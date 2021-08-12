package org.mesonet.dataprocessing.site.forecast

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.core.PerContext
import org.mesonet.dataprocessing.ConnectivityStatusProvider
import org.mesonet.dataprocessing.PageStateInfo

import org.mesonet.dataprocessing.formulas.UnitConverter
import org.mesonet.dataprocessing.site.MesonetSiteDataController
import org.mesonet.dataprocessing.userdata.Preferences
import org.mesonet.models.site.forecast.Forecast
import org.mesonet.network.DataProvider
import java.util.concurrent.TimeUnit

import javax.inject.Inject
import kotlin.collections.ArrayList

@PerContext
class FiveDayForecastDataControllerImpl @Inject constructor(private var mMesonetSiteDataController: MesonetSiteDataController,
                                                            private var mPreferences: Preferences,
                                                            private var mUnitConverter: UnitConverter,
                                                            private var mDataProvider: DataProvider,
                                                            private var mConnectivityStatusProvider: ConnectivityStatusProvider) : FiveDayForecastDataController, Observer<Pair<List<Forecast>, String>> {
    private var mDataSubject: BehaviorSubject<List<SemiDayForecastDataController>> = BehaviorSubject.create()

    private var mUpdateDisposable: Disposable? = null
    private var mConnectivityDisposable: Disposable? = null
    private var mCurrentSelectionDisposable: Disposable? = null

    private var mPageStateSubject: BehaviorSubject<PageStateInfo> = BehaviorSubject.create()
    private var mUpdateObservable: Observable<Pair<List<Forecast>, String>>? = null
    private lateinit var mTickObservable: Observable<Long>
    private lateinit var mConnectivityObservable: Observable<Boolean>
    private lateinit var mSiteObservable: Observable<MesonetSiteDataController.ProcessedMesonetSite>
    private lateinit var mDataObservable: Observable<Pair<List<Forecast>, String>>

    private var mLastUpdateStid: String = ""
    private var mLastSelectedStid: String = ""

    private var mContext: Context? = null


    override fun OnCreate(inContext: Context) {
        mMesonetSiteDataController.OnCreate(inContext)

        if(!mPageStateSubject.hasValue()) {
            mPageStateSubject.onNext(object : PageStateInfo {
                override fun GetPageState(): PageStateInfo.PageState {
                    return PageStateInfo.PageState.kLoading
                }

                override fun GetErrorMessage(): String? {
                    return null
                }
            })
        }

        mMesonetSiteDataController.GetCurrentSelectionObservable().observeOn(Schedulers.computation()).subscribe(object: Observer<MesonetSiteDataController.ProcessedMesonetSite>{
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable)
            {
                mCurrentSelectionDisposable = d
            }

            override fun onNext(t: MesonetSiteDataController.ProcessedMesonetSite) {
                mLastSelectedStid = t.GetStid()

                if (mLastSelectedStid == mLastUpdateStid) {
                    mPageStateSubject.onNext(object : PageStateInfo {
                        override fun GetPageState(): PageStateInfo.PageState {
                            return PageStateInfo.PageState.kData
                        }

                        override fun GetErrorMessage(): String? {
                            return ""
                        }
                    })
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })


        mConnectivityStatusProvider.ConnectivityStatusObservable().observeOn(Schedulers.computation()).subscribe(object: Observer<Boolean>{
            override fun onComplete() {}

            override fun onSubscribe(d: Disposable)
            {
                mConnectivityDisposable = d
            }

            override fun onNext(t: Boolean) {
                mPageStateSubject.onNext(
                        if(mLastSelectedStid == mLastUpdateStid && mLastSelectedStid.isNotBlank()) {
                            object : PageStateInfo {
                                override fun GetPageState(): PageStateInfo.PageState {
                                    return PageStateInfo.PageState.kData
                                }

                                override fun GetErrorMessage(): String? {
                                    return ""
                                }

                            }
                        }
                        else if(t)
                        {
                            object: PageStateInfo {
                                override fun GetPageState(): PageStateInfo.PageState {
                                    return PageStateInfo.PageState.kLoading
                                }

                                override fun GetErrorMessage(): String? {
                                    return ""
                                }
                            }
                        }
                        else
                        {
                            object : PageStateInfo {
                                override fun GetPageState(): PageStateInfo.PageState {
                                    return PageStateInfo.PageState.kError
                                }

                                override fun GetErrorMessage(): String? {
                                    return "No Connection"
                                }

                            }
                        })

                mConnectivityDisposable?.dispose()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })
    }

    override fun OnResume(inContext: Context) {
        mMesonetSiteDataController.OnResume(inContext)

        mContext = inContext

        if(mUpdateDisposable?.isDisposed != false) {
            mConnectivityObservable = mConnectivityStatusProvider.ConnectivityStatusObservable()
            mTickObservable = Observable.interval(0, 1, TimeUnit.MINUTES).distinctUntilChanged{it -> it}.retryWhen { mMesonetSiteDataController.GetCurrentSelectionObservable() }.retryWhen { mConnectivityObservable }.subscribeOn(Schedulers.computation())
            mSiteObservable = mMesonetSiteDataController.GetCurrentSelectionObservable().distinctUntilChanged { site -> site.GetStid() }.retryWhen { mConnectivityObservable }.retryWhen { mTickObservable }.subscribeOn(Schedulers.computation())

            mDataObservable = Observables.combineLatest(mTickObservable,
                    mConnectivityObservable,
                    mSiteObservable.observeOn(Schedulers.computation()))
            { _, connectivity, site ->
                mLastSelectedStid = site.GetStid()
                site
            }.retryWhen { mSiteObservable }.retryWhen { mConnectivityObservable }.retryWhen { mTickObservable }.flatMap{site ->
                mDataProvider.GetForecast(site.GetStid()).retryWhen { mSiteObservable }.retryWhen { mConnectivityObservable }.retryWhen { mTickObservable }
            }.retryWhen { mSiteObservable }.retryWhen { mConnectivityObservable }.retryWhen { mTickObservable }.subscribeOn(Schedulers.computation())

            mUpdateObservable = Observables.combineLatest(
                    mDataObservable,
                    mSiteObservable.subscribeOn(Schedulers.computation()),
                    mConnectivityObservable.subscribeOn(Schedulers.computation()))
            {
                data, site, connectivity ->

                if (mDataSubject.hasValue() && mLastUpdateStid != data.second) {
                    for (forecastController in mDataSubject.value?: ArrayList()) {
                        forecastController.StartReloading()
                    }
                }

                if(!mDataSubject.hasValue() || mDataSubject.value?.isEmpty() != false) {
                    val result = ArrayList<SemiDayForecastDataController>()

                    val myContext = mContext
                    if(myContext != null) {
                        for (i in 0..9) {
                            if (i < data.first.size)
                                result.add(SemiDayForecastDataController(myContext, mPreferences, mUnitConverter, data.first[i], mDataProvider, i))
                            else
                                result.add(SemiDayForecastDataController(myContext, mPreferences, mUnitConverter, null, mDataProvider, i))
                        }
                    }

                    mDataSubject.onNext(result)
                }
                else
                {
                    for(i in 0..9)
                    {
                        if(i < data.first.size)
                            mDataSubject.value?.get(i)?.SetData(data.first[i])
                        else
                            mDataSubject.value?.get(i)?.SetEmpty()
                    }
                }

                when {
                    site.GetStid() == data.second -> mPageStateSubject.onNext(object : PageStateInfo {
                        override fun GetPageState(): PageStateInfo.PageState {
                            return PageStateInfo.PageState.kData
                        }

                        override fun GetErrorMessage(): String? {
                            return null
                        }

                    })
                    connectivity -> mPageStateSubject.onNext(object : PageStateInfo {
                        override fun GetPageState(): PageStateInfo.PageState {
                            return PageStateInfo.PageState.kLoading
                        }

                        override fun GetErrorMessage(): String? {
                            return ""
                        }

                    })
                    else -> mPageStateSubject.onNext(object : PageStateInfo {
                        override fun GetPageState(): PageStateInfo.PageState {
                            return PageStateInfo.PageState.kError
                        }

                        override fun GetErrorMessage(): String? {
                            return "No Connection"
                        }

                    })
                }

                mLastUpdateStid = data.second

                data
            }.retryWhen { mSiteObservable }.retryWhen { mConnectivityObservable }.retryWhen { mTickObservable }?.subscribeOn(Schedulers.computation())

            mUpdateObservable?.observeOn(Schedulers.computation())?.subscribe(this)
        }
    }

    override fun OnPause() {
        mMesonetSiteDataController.OnPause()
        mUpdateDisposable?.dispose()
        mUpdateDisposable = null
    }

    override fun OnDestroy()
    {
        mMesonetSiteDataController.OnDestroy()

        if(mDataSubject.hasValue())
        {
            for(forecastController in mDataSubject.value?: ArrayList())
            {
                forecastController.Dispose()
            }
        }
        mUpdateDisposable?.dispose()
        mUpdateDisposable = null
        mConnectivityDisposable?.dispose()
        mConnectivityDisposable = null
        mPageStateSubject.onComplete()

        mDataSubject.onComplete()
    }


    override fun GetPageStateObservable(): Observable<PageStateInfo>
    {
        return mPageStateSubject
    }


    override fun GetConnectivityStateObservable(): Observable<Boolean> {
        return mConnectivityStatusProvider.ConnectivityStatusObservable().takeWhile{!mDataSubject.hasValue() || mLastUpdateStid != mMesonetSiteDataController.CurrentSelection()}.retryWhen { mMesonetSiteDataController.GetCurrentSelectionObservable().distinctUntilChanged{ site -> site.GetStid() } }.retryWhen { mConnectivityStatusProvider.ConnectivityStatusObservable().distinctUntilChanged{status -> status} }
    }


    override fun GetForecastDataSubject(inContext: Context): BehaviorSubject<List<SemiDayForecastDataController>>
    {
        return mDataSubject
    }


    override fun onComplete()
    {

    }

    override fun onSubscribe(d: Disposable) {
        mUpdateDisposable?.dispose()
        mUpdateDisposable = d
    }

    override fun onNext(t: Pair<List<Forecast>, String>)
    {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }



    override fun GetCount(): Int {
        return mDataSubject.value?.size?: 0
    }


    override fun GetForecast(inIndex: Int): SemiDayForecastDataController {
        if(!mDataSubject.hasValue())
            throw IllegalStateException()

        return mDataSubject.value?.get(inIndex) ?: throw IllegalStateException()
    }
}