package org.mesonet.dataprocessing.site


import android.location.Location
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.cache.site.SiteCache
import org.mesonet.core.PerContext
import org.mesonet.dataprocessing.BasicListData
import org.mesonet.dataprocessing.LocationProvider
import org.mesonet.dataprocessing.userdata.Preferences
import org.mesonet.models.site.MesonetSiteList
import org.mesonet.network.DataProvider

import java.text.ParseException
import java.text.SimpleDateFormat

import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import android.content.Context
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import org.mesonet.dataprocessing.ConnectivityStatusProvider
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean


@PerContext
class MesonetSiteDataControllerImpl @Inject
constructor(internal var mLocationProvider: LocationProvider,
            internal var mCache: SiteCache,
            internal var mPreferences: Preferences,
            internal val mDataProvider: DataProvider,
            internal val mConnectivityStatusProvider: ConnectivityStatusProvider): MesonetSiteDataController
{
    private val kDefaultSelection = "nrmn"

    private val mCurrentSelectionSubject: BehaviorSubject<MesonetSiteDataController.ProcessedMesonetSite> = BehaviorSubject.create()

    private val mFavorites: BehaviorSubject<MutableList<String>> = BehaviorSubject.create()
    private val mMesonetSiteListSubject: BehaviorSubject<Map<String, MesonetSiteList.MesonetSite>> = BehaviorSubject.create()

    private var mCreated = false

    private var mManualSelection = AtomicBoolean(false)

    private var mCompositeDisposable = CompositeDisposable()

    private lateinit var mDownloadObservable: Observable<Map<String, MesonetSiteList.MesonetSite>> // need to hang on to a reference of the observable so it will retry


    override fun OnCreate(inContext: Context) {
        synchronized(this)
        {
            if(!mCreated) {
                mCreated = true
                mCache.GetSites(inContext).takeIf { !mMesonetSiteListSubject.hasValue() }?.subscribeOn(Schedulers.computation())?.subscribe(object : SingleObserver<Map<String, MesonetSiteList.MesonetSite>> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onSuccess(t: Map<String, MesonetSiteList.MesonetSite>) {
                        if(t.isNotEmpty())
                            mMesonetSiteListSubject.onNext(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })

                mCache.GetFavorites(inContext).takeIf { !mFavorites.hasValue() }?.subscribeOn(Schedulers.computation())?.subscribe(object : SingleObserver<MutableList<String>> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onSuccess(t: MutableList<String>) {
                        mFavorites.onNext(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })

                mMesonetSiteListSubject.subscribeOn(Schedulers.computation()).subscribe(object: Observer<Map<String, MesonetSiteList.MesonetSite>> {
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(t: Map<String, MesonetSiteList.MesonetSite>) {
                        mCache.SaveSites(inContext, t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })

                Observables.combineLatest(mMesonetSiteListSubject,
                        mFavorites,
                        mLocationProvider.GetLocation().toObservable(),
                        mPreferences.SelectedStidObservable(inContext).distinctUntilChanged { stid -> stid })
                { siteList, favorites, location, cachedSite ->
                    var site = cachedSite

                    if (site.isBlank() && location.LocationResult() != null)
                        site = GetNearestSite(siteList, location.LocationResult())

                    if (site.isBlank() && mMesonetSiteListSubject.hasValue() && mMesonetSiteListSubject.value?.filter { it.key == kDefaultSelection }?.entries?.isNotEmpty()?: false)
                        site = kDefaultSelection

                    ProcessedMesonetSiteImpl(site,
                            favorites.contains(site),
                            mDataProvider.GetMeteogramImageUrl(site.toUpperCase()),
                            siteList[site]?.GetLat(),
                            siteList[site]?.GetElev(),
                            siteList[site]?.GetStnm(),
                            siteList[site]?.GetName(),
                            siteList[site]?.GetLon(),
                            siteList[site]?.GetDatd())
                }.takeWhile{!mManualSelection.get()}.subscribeOn(Schedulers.computation()).subscribe(object : Observer<MesonetSiteDataController.ProcessedMesonetSite> {
                    var disposable: Disposable? = null
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable)
                    {
                        disposable = d
                    }
                    override fun onNext(t: MesonetSiteDataController.ProcessedMesonetSite) {
                        mCurrentSelectionSubject.onNext(t)

                        disposable?.dispose()
                        disposable = null
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })

                mDownloadObservable = mDataProvider.GetMesonetSites().map { list ->
                    list.filter {
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss z", Locale.US)

                        var date: Date? = null

                        try {
                            date = dateFormat.parse(it.value.GetDatd())
                        } catch (e: ParseException) {
                            e.printStackTrace()
                        } catch (e: NullPointerException) {
                            e.printStackTrace()
                        }

                        date != null && date.after(Date())
                    }
                }.retryWhen { mConnectivityStatusProvider.ConnectivityStatusObservable() }.subscribeOn(Schedulers.computation())

                mDownloadObservable.observeOn(Schedulers.computation()).subscribe(object : Observer<Map<String, MesonetSiteList.MesonetSite>> {
                    var disposable: Disposable? = null
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable)
                    {
                        disposable?.dispose()
                        disposable = d
                    }

                    override fun onNext(t: Map<String, MesonetSiteList.MesonetSite>) {
                        mMesonetSiteListSubject.onNext(t)
                        disposable?.dispose()
                        disposable = null
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })

                mCurrentSelectionSubject.flatMap { it -> mPreferences.SetSelectedStid(inContext, it.GetStid()).toObservable() }.subscribeOn(Schedulers.io()).subscribe(object: Observer<Int>{
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(t: Int) {}

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })

                mFavorites.subscribeOn(Schedulers.io()).subscribe(object : Observer<List<String>> {
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(t: List<String>) {
                        mCache.SaveFavorites(inContext, t)
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
            }
        }
    }


    override fun OnResume(inContext: Context){}


    override fun OnPause(){}
    override fun OnDestroy() {
        mCurrentSelectionSubject.onComplete()
        mFavorites.onComplete()
        mMesonetSiteListSubject.onComplete()
        mCreated = false
    }


    override fun CurrentSelection(): String {
        if(mCurrentSelectionSubject.hasValue())
            return mCurrentSelectionSubject.value?.GetStid() ?: ""

        return ""
    }


    override fun GetCurrentSelectionObservable(): Observable<MesonetSiteDataController.ProcessedMesonetSite>
    {
        return mCurrentSelectionSubject.subscribeOn(Schedulers.computation())
    }


    override fun SetResult(inResult: String) {
        if(mMesonetSiteListSubject.hasValue() && mCurrentSelectionSubject.value?.GetStid() != inResult) {
            mManualSelection.set(true)
            var result = mMesonetSiteListSubject.value?.filter { it.key == inResult }?.entries?.first()

            if(result == null)
                result = mMesonetSiteListSubject.value?.filter { it.key == kDefaultSelection }?.entries?.first()

            if(result != null)
                mCurrentSelectionSubject.onNext(ProcessedMesonetSiteImpl(result.key,
                                                              mFavorites.hasValue() && mFavorites.value?.contains(result.key) == true,
                                                                        mDataProvider.GetMeteogramImageUrl(result.key.toUpperCase()),
                                                                        result.value.GetLat(),
                                                                        result.value.GetElev(),
                                                                        result.value.GetStnm(),
                                                                        result.value.GetName(),
                                                                        result.value.GetLon(),
                                                                        result.value.GetDatd()))
        }
    }



    private fun GetNearestSite(inMesonetSiteList: Map<String, MesonetSiteList.MesonetSite>, inLocation: Location?): String {
        if(inLocation == null)
            return ""

        val keys = ArrayList(inMesonetSiteList.keys)

        var shortestDistanceIndex = -1
        var shortestDistance = java.lang.Float.MAX_VALUE

        for (i in keys.indices) {
            val site = inMesonetSiteList[keys[i]]

            if(site != null) {
                val siteLocation = Location("none")

                siteLocation.latitude = java.lang.Double.parseDouble(site.GetLat()?: "0.0")
                siteLocation.longitude = java.lang.Double.parseDouble(site.GetLon()?: "0.0")

                val distance = siteLocation.distanceTo(inLocation)

                if (distance < shortestDistance) {
                    shortestDistance = distance
                    shortestDistanceIndex = i
                }
            }
        }

        return if (shortestDistanceIndex == -1) "" else keys[shortestDistanceIndex]

    }


    override fun AsBasicListData(): Observable<Pair<Map<String, BasicListData>, String>> {
        return Observables.combineLatest(mMesonetSiteListSubject,
                mFavorites,
                mCurrentSelectionSubject){
            list, favorites, currentSelection ->
            Pair(MakeMesonetStidNamePairs(list, favorites), currentSelection.GetStid())
        }/*Single.create(SingleOnSubscribe<Pair<Map<String, BasicListData>, String>> {
            synchronized(this@MesonetSiteDataControllerImpl)
            {
                if(mMesonetSiteListSubject.hasValue()) {
                    it.onSuccess(Pair(MakeMesonetStidNamePairs(mMesonetSiteListSubject.value?: HashMap<String, MesonetSiteList.MesonetSite>() as MesonetSiteList, if(mFavorites.hasValue()) mFavorites.value?: ArrayList() else ArrayList()), mCurrentSelectionSubject.value?.GetStid()?: kDefaultSelection))
                }
            }
        }).subscribeOn(Schedulers.computation())*/
    }


    override fun ToggleFavorite(inStid: String): Observable<Boolean>
    {
        return Observable.create{
            if (IsFavorite(inStid)) {
                RemoveFavorite(inStid)
            } else {
                AddFavorite(inStid)
            }

            it.onNext(IsFavorite(inStid))
            it.onComplete()
        }
    }


    internal fun IsFavorite(inStid: String): Boolean {
        return mFavorites.hasValue() && mFavorites.value?.contains(inStid)?: false
    }

    internal fun AddFavorite(inStid: String) {
        if(!mFavorites.hasValue() || mFavorites.value?.contains(inStid) != true)
        {
            var result: MutableList<String> = ArrayList()

            if(mFavorites.hasValue())
                result = ArrayList(mFavorites.value?: ArrayList())

            result.add(inStid)

            mFavorites.onNext(result)
        }
    }

    internal fun RemoveFavorite(inStid: String) {
        if(mFavorites.hasValue() && mFavorites.value?.contains(inStid) == true)
        {
            var result = ArrayList(mFavorites.value?: ArrayList())

            result.remove(inStid)

            mFavorites.onNext(result)
        }
    }


    internal fun MakeMesonetStidNamePairs(inSiteList : Map<String, MesonetSiteList.MesonetSite>, inFavorites : List<String>) : Map<String, BasicListData>
    {
        val result = HashMap<String, BasicListData>()

        inSiteList.forEach {
            val location = Location("none")
            location.latitude = it.value.GetLat()?.toDouble() ?: 0.0
            location.longitude = it.value.GetLon()?.toDouble() ?: 0.0
            result.put(it.key, object: BasicListData {
                override fun GetName(): String {
                    return it.value.GetName()?: ""
                }

                override fun GetSortString(): String {
                    return GetName()
                }

                override fun IsFavorite(): Boolean {
                    return inFavorites.contains(it.key)
                }

                override fun GetLocation(): Location {
                    return location
                }

            })
        }

        return result
    }


    private class ProcessedMesonetSiteImpl(private var mStid: String,
                                           private var mIsFavorite: Boolean,
                                           private var mMeteogramUrl: String,
                                           private var mLat: String?,
                                           private var mElev: String?,
                                           private var mStnm: String?,
                                           private var mName: String?,
                                           private var mLon: String?,
                                           private var mDatd: String?): MesonetSiteDataController.ProcessedMesonetSite
    {
        override fun compareTo(inOther: MesonetSiteDataController.ProcessedMesonetSite): Int {
            return GetStid().compareTo(inOther.GetStid())
        }

        override fun GetStid(): String {
            return mStid
        }

        override fun IsFavorite(): Boolean {
            return mIsFavorite
        }

        override fun GetMeteogramUrl(): String {
            return mMeteogramUrl
        }

        override fun GetLat(): String? {
            return mLat
        }

        override fun GetElev(): String? {
            return mElev
        }

        override fun GetStnm(): String? {
            return mStnm
        }

        override fun GetName(): String? {
            return mName
        }

        override fun GetLon(): String? {
            return mLon
        }

        override fun GetDatd(): String? {
            return mDatd
        }

    }


}