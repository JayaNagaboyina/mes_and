package org.mesonet.dataprocessing.radar

import android.app.Activity
import android.location.Location
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.core.PerContext
import org.mesonet.dataprocessing.BasicListData
import org.mesonet.dataprocessing.userdata.Preferences
import org.mesonet.models.radar.RadarDetails
import org.mesonet.models.radar.RadarDetailCreator

import javax.inject.Inject


@PerContext
class RadarSiteDataProviderImpl @Inject
constructor(inContext: Activity, var mRadarDetailCreator: RadarDetailCreator, internal var mPreferences: Preferences) : RadarSiteDataProvider {

    private var mSelectedSiteInfoSubject: BehaviorSubject<Map.Entry<String, RadarDetails>> = BehaviorSubject.create()

    private var mSiteSubject: BehaviorSubject<Map<String, RadarDetails>> = BehaviorSubject.create()

    private var mSiteDisposable: Disposable? = null


    init {
        Observables.combineLatest(Observable.create(ObservableOnSubscribe<String> {
                        it.onNext(inContext.resources.openRawResource(inContext.resources.getIdentifier("radar_list", "raw", inContext.packageName)).bufferedReader().use { it.readText() })
                        it.onComplete()
                    }).subscribeOn(Schedulers.io()),
                mPreferences.SelectedRadarObservable(inContext))
        {
            siteString, selectedSite ->
            val sites = mRadarDetailCreator.ParseRadarJson(siteString)

            Pair(sites, selectedSite)
        }.subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(object: Observer<Pair<Map<String, RadarDetails>, String>>{
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: Pair<Map<String, RadarDetails>, String>) {
                    var selectedSite = t.second

                    if(selectedSite.isBlank())
                        selectedSite = "KTLX"

                    if(t.first.containsKey(selectedSite) && (!mSelectedSiteInfoSubject.hasValue() || mSelectedSiteInfoSubject.value?.key != selectedSite)) {
                        mSelectedSiteInfoSubject.onNext(t.first.entries.first { it.key == selectedSite })
                    }

                    mSiteSubject.onNext(t.first)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })

        mSelectedSiteInfoSubject.flatMap {
            mPreferences.SetSelectedRadar(inContext, it.key).toObservable()
        }.subscribe(object: Observer<Int>{
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: Int) {}
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })
    }



    override fun GetSelectedSiteInfoObservable(): Observable<Map.Entry<String, RadarDetails>>
    {
        return mSelectedSiteInfoSubject
    }


    override fun CurrentSelection(): String {
        if(!mSelectedSiteInfoSubject.hasValue())
            return ""

        return mSelectedSiteInfoSubject.value?.key?: ""
    }


    override fun AsBasicListData(): Observable<Pair<Map<String, BasicListData>, String>>
    {
        return Observable.create (
            ObservableOnSubscribe<Pair<Map<String, BasicListData>, String>> {
            val resultMap: Map<String, BasicListData> = mSiteSubject.value?.mapValues {
                object : BasicListData {
                    override fun GetName(): String {
                        return "${it.key} - ${it.value.GetName()}"
                    }

                    override fun GetSortString(): String {
                        return "${it.value.GetName()}"
                    }

                    override fun IsFavorite(): Boolean {
                        return false
                    }

                    override fun GetLocation(): Location {
                        val location = Location("none")

                        location.latitude = it.value.GetLatitude()?.toDouble()?: 0.0
                        location.longitude = it.value.GetLongitude()?.toDouble()?: 0.0

                        return location
                    }

                }
            }?: HashMap()

            it.onNext(Pair(resultMap, mSelectedSiteInfoSubject.value?.key?: ""))
        }).subscribeOn(Schedulers.computation())
    }


    override fun SetResult(inResult: String) {
        if(mSiteSubject.hasValue() && mSiteSubject.value?.containsKey(inResult) == true)
            mSelectedSiteInfoSubject.onNext(mSiteSubject.value?.entries?.first{ it.key == inResult }!!)
    }


    override fun Dispose()
    {
        mSiteDisposable?.dispose()
        mSiteDisposable = null
        mSelectedSiteInfoSubject.onComplete()
        mSiteSubject.onComplete()
    }
}