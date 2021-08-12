package org.mesonet.dataprocessing.radar

import android.content.Context
import android.graphics.Bitmap
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.core.PerContext
import org.mesonet.dataprocessing.ConnectivityStatusProvider
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.models.radar.RadarDetails
import org.mesonet.models.radar.RadarHistory
import org.mesonet.network.DataProvider
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong

import javax.inject.Inject
import kotlin.collections.ArrayList


@PerContext
class RadarDataController @Inject
constructor(internal var mSiteDataProvider: RadarSiteDataProvider,
            internal var mDataProvider: DataProvider,
            internal var mConnectivityStatusProvider: ConnectivityStatusProvider) : RadarImageDataProvider {

    private var mUpdateDisposable: Disposable? = null
    private var mConnectivityDisposable: Disposable? = null

    private val mRadarImageDataSubject: BehaviorSubject<ArrayList<ImageInfoImpl>> = BehaviorSubject.create()
    private val mPageStateSubject: BehaviorSubject<PageStateInfo> = BehaviorSubject.create()
    private val mConnecectivityObservable = mConnectivityStatusProvider.ConnectivityStatusObservable()
    private lateinit var mTickObservable: Observable<Long>
    private lateinit var mSiteObservable: Observable<Map.Entry<String, RadarDetails>>

    private var mLastSiteSelected = ""
    private var mLastSiteLoaded = ""

    private var mEmptyImage = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8)



    override fun GetSiteInfoObservable(): Observable<Map.Entry<String, RadarDetails>>
    {
        return mSiteDataProvider.GetSelectedSiteInfoObservable()
    }


    override fun GetRadarAnimationObservable(): Observable<List<ImageInfo>> {
        return mRadarImageDataSubject.map{ it as List<ImageInfo> }
    }


    private fun MakeId(inRadarId: String, inFrame: RadarHistory.RadarFrame): String {
        return inRadarId + inFrame.GetTime()
    }


    override fun OnCreate(inContext: Context) {}


    override fun OnResume(inContext: Context)
    {
        if(mUpdateDisposable?.isDisposed != false) {
            mTickObservable = Observable.interval(0, 1, TimeUnit.MINUTES).distinctUntilChanged{tick -> tick}.retryWhen { mConnecectivityObservable }
            mSiteObservable = GetSiteInfoObservable().retryWhen { mTickObservable }.retryWhen { mConnecectivityObservable }
            Observables.combineLatest(
                Observables.combineLatest(mTickObservable, mConnecectivityObservable, mSiteObservable)
                { _, connectivity, site ->
                    mLastSiteSelected = site.key

                    if(mLastSiteSelected != mLastSiteLoaded)
                    {
                        if(connectivity) {
                            mPageStateSubject.onNext(object : PageStateInfo {
                                override fun GetPageState(): PageStateInfo.PageState {
                                    return PageStateInfo.PageState.kLoading
                                }

                                override fun GetErrorMessage(): String? {
                                    return ""
                                }

                            })
                        }
                        else
                        {
                            mPageStateSubject.onNext(object: PageStateInfo{
                                override fun GetPageState(): PageStateInfo.PageState {
                                    return PageStateInfo.PageState.kError
                                }

                                override fun GetErrorMessage(): String? {
                                    return "No Connection"
                                }

                            })
                        }
                    }
                    else if(!connectivity)
                    {
                        mPageStateSubject.onNext(object: PageStateInfo{
                            override fun GetPageState(): PageStateInfo.PageState {
                                return PageStateInfo.PageState.kData
                            }

                            override fun GetErrorMessage(): String? {
                                return ""
                            }

                        })
                    }

                    site
                }.retryWhen { mTickObservable }.retryWhen { mSiteObservable }.retryWhen { mConnecectivityObservable }.flatMap {
                    site ->
                    mDataProvider.GetRadarHistory(site.key).retryWhen { mTickObservable }.retryWhen { mSiteObservable }.retryWhen { mConnecectivityObservable }
                }.retryWhen { mTickObservable }.retryWhen { mSiteObservable }.retryWhen { mConnecectivityObservable },
                mConnecectivityObservable)
            { history, connectivity ->
                Pair(history, connectivity)
            }.retryWhen { mTickObservable }.retryWhen { mSiteObservable }.retryWhen { mConnecectivityObservable }.flatMap {
                historyConnectivityPair ->

                if(historyConnectivityPair.second) {
                    val relevantList = historyConnectivityPair.first.GetFrames().subList(0, 6)

                    var resultList = ArrayList<ImageInfoImpl>()
                    val buffer = HashMap<String, Bitmap>()

                    if (mRadarImageDataSubject.hasValue()) {
                        resultList = ArrayList(mRadarImageDataSubject.value?: ArrayList())

                        for (i in resultList.indices) {
                            val image = mRadarImageDataSubject.value?.first { it.GetName() == resultList[i].GetName() && it.GetImage() != mEmptyImage }?.GetImage()

                            if (image != null)
                                buffer[resultList[i].GetName()] = image
                        }
                    }

                    val needsToDownload = relevantList.filterNot { frame -> resultList.map { it.GetName() }.contains(MakeId(historyConnectivityPair.first.GetRadar(), frame)) }.distinct()

                    buffer.filter{ buf -> relevantList.find { rel -> MakeId(historyConnectivityPair.first.GetRadar(), rel) == buf.key } == null }.forEach{ it -> it.value.recycle() }

                    val downloadObservables = HashMap<String, Observable<Bitmap>>()

                    for (i in needsToDownload.indices) {
                        downloadObservables[MakeId(historyConnectivityPair.first.GetRadar(), needsToDownload[i])] = mDataProvider.GetRadarImage(historyConnectivityPair.first.GetRadar(), needsToDownload[i].GetFrameId())
                    }

                    val observableList = ArrayList<Observable<ImageInfoImpl>>()

                    for (i in relevantList.indices) {
                        if (downloadObservables.containsKey(MakeId(historyConnectivityPair.first.GetRadar(), relevantList[i]))) {
                            val observable = downloadObservables[MakeId(historyConnectivityPair.first.GetRadar(), relevantList[i])]

                            if (observable != null)
                                observableList.add(observable.map { image -> ImageInfoImpl(MakeId(historyConnectivityPair.first.GetRadar(), relevantList[i]), relevantList[i].GetTimestring(), image) })
                            else
                                observableList.add(Observable.create {
                                    it.onNext(ImageInfoImpl(MakeId(historyConnectivityPair.first.GetRadar(), relevantList[i]), relevantList[i].GetTimestring(), buffer[MakeId(historyConnectivityPair.first.GetRadar(), relevantList[i])]
                                            ?: mEmptyImage))
                                    it.onComplete()
                                })
                        } else {
                            observableList.add(Observable.create {
                                it.onNext(ImageInfoImpl(MakeId(historyConnectivityPair.first.GetRadar(), relevantList[i]), relevantList[i].GetTimestring(), buffer[MakeId(historyConnectivityPair.first.GetRadar(), relevantList[i])]
                                        ?: mEmptyImage))
                                it.onComplete()
                            })
                        }
                    }

                    assert(observableList.size == 6)

                    Observables.combineLatest(observableList[0],
                            observableList[1],
                            observableList[2],
                            observableList[3],
                            observableList[4],
                            observableList[5])
                    { imageInfo0, imageInfo1, imageInfo2, imageInfo3, imageInfo4, imageinfo5 ->

                        mLastSiteLoaded = mLastSiteSelected

                        Pair<PageStateInfo, ArrayList<ImageInfoImpl>>(object: PageStateInfo{
                            override fun GetPageState(): PageStateInfo.PageState {
                                return PageStateInfo.PageState.kData
                            }

                            override fun GetErrorMessage(): String? {
                                return ""
                            }
                        }, ArrayList(Arrays.asList(imageInfo0, imageInfo1, imageInfo2, imageInfo3, imageInfo4, imageinfo5)))
                    }
                }
                else {
                    if (mLastSiteLoaded == mLastSiteSelected && mLastSiteLoaded.isNotBlank()) {
                        Observable.create {
                            it.onNext(Pair<PageStateInfo, ArrayList<ImageInfoImpl>>(object: PageStateInfo{
                                override fun GetPageState(): PageStateInfo.PageState {
                                    return PageStateInfo.PageState.kData
                                }

                                override fun GetErrorMessage(): String? {
                                    return ""
                                }
                            }, mRadarImageDataSubject.value?: ArrayList()))
                        }

                    } else
                    {
                        Observable.create {
                            it.onNext(Pair<PageStateInfo, ArrayList<ImageInfoImpl>>(object: PageStateInfo {
                                override fun GetPageState(): PageStateInfo.PageState {
                                    return PageStateInfo.PageState.kError
                                }

                                override fun GetErrorMessage(): String? {
                                    return "No Connection"

                                }
                            }, ArrayList()))
                        }
                    }
                }
            }.retryWhen { mTickObservable }.retryWhen { mSiteObservable }.retryWhen { mConnecectivityObservable }.subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(object: Observer<Pair<PageStateInfo, ArrayList<ImageInfoImpl>>>{
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable)
                {
                    mUpdateDisposable?.dispose()
                    mUpdateDisposable = d
                }

                override fun onNext(t: Pair<PageStateInfo, ArrayList<ImageInfoImpl>>) {
                    if(t.first.GetPageState() == PageStateInfo.PageState.kData)
                        mRadarImageDataSubject.onNext(t.second)

                    mPageStateSubject.onNext(t.first)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        }
    }



    override fun OnPause()
    {
        mUpdateDisposable?.dispose()
        mUpdateDisposable = null
        mConnectivityDisposable?.dispose()
        mConnectivityDisposable = null
    }


    override fun OnDestroy()
    {
        mSiteDataProvider.Dispose()

        if(mRadarImageDataSubject.hasValue()) {
            for (frame in mRadarImageDataSubject.value?: ArrayList())
                frame.GetImage().recycle()
        }

        mRadarImageDataSubject.onComplete()
        mPageStateSubject.onComplete()

        mEmptyImage.recycle()
    }


    override fun GetPageStateObservable(): Observable<PageStateInfo> {
        return mPageStateSubject
    }


    inner class ImageInfoImpl(var mName: String = "",
                              var mTimestring: String = "",
                              var mImage: Bitmap = mEmptyImage) : ImageInfo
    {
        override fun GetName(): String {
            return mName
        }

        override fun GetTimestring(): String {
            return mTimestring
        }

        override fun GetImage(): Bitmap {
            return mImage
        }
    }


    interface ImageInfo
    {
        fun GetName(): String
        fun GetTimestring(): String
        fun GetImage(): Bitmap
    }
}