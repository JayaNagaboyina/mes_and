package org.mesonet.dataprocessing.advisories

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
import org.mesonet.models.advisories.Advisory
import org.mesonet.network.DataProvider
import java.util.concurrent.TimeUnit

import javax.inject.Inject
import javax.inject.Singleton


@PerContext
class AdvisoryDataProviderImpl @Inject constructor(internal var mDataProvider: DataProvider,
                                                   internal var mConnectivityStatusProvider: ConnectivityStatusProvider): AdvisoryDataProvider
{
    private var mAdvisoryDisposable: Disposable? = null

    private val mDataSubject: BehaviorSubject<Advisory.AdvisoryList> = BehaviorSubject.create()
    private val mPageStateSubject: BehaviorSubject<PageStateInfo> = BehaviorSubject.create()

    private lateinit var mTickObservable: Observable<Long>
    private lateinit var mUpdateObservable: Observable<Advisory.AdvisoryList>
    private val mConnectivityStatusObservable = mConnectivityStatusProvider.ConnectivityStatusObservable()


    override fun GetDataObservable(): Observable<Advisory.AdvisoryList>
    {
        return mDataSubject
    }


    override fun GetPageStateObservable(): Observable<PageStateInfo>
    {
        return mPageStateSubject
    }


    override fun OnCreate(inContext: Context) {}

    override fun OnResume(inContext: Context) {
        if(mAdvisoryDisposable?.isDisposed != false) {
            mTickObservable = Observable.interval(0, 1, TimeUnit.MINUTES)
            mUpdateObservable = Observables.combineLatest(mTickObservable,
                    mConnectivityStatusObservable)
            {
                _, connectivity ->

                when {
                    mDataSubject.hasValue() -> mPageStateSubject.onNext(object: PageStateInfo{
                        override fun GetPageState(): PageStateInfo.PageState {
                            return PageStateInfo.PageState.kData
                        }

                        override fun GetErrorMessage(): String? {
                            return ""
                        }

                    })
                    connectivity -> mPageStateSubject.onNext(object: PageStateInfo{
                        override fun GetPageState(): PageStateInfo.PageState {
                            return PageStateInfo.PageState.kLoading
                        }

                        override fun GetErrorMessage(): String? {
                            return ""
                        }

                    })
                    else -> mPageStateSubject.onNext(object: PageStateInfo {
                        override fun GetPageState(): PageStateInfo.PageState {
                            return PageStateInfo.PageState.kError
                        }

                        override fun GetErrorMessage(): String? {
                            return "No Connection"
                        }

                    })
                }

            }.flatMap {_ ->
                mDataProvider.GetAdvisoriesList().retryWhen { mTickObservable }.retryWhen { mConnectivityStatusObservable }
            }.retryWhen { mTickObservable }.retryWhen { mConnectivityStatusObservable }.subscribeOn(Schedulers.computation())

            mUpdateObservable.observeOn(Schedulers.computation()).subscribe(object : Observer<Advisory.AdvisoryList> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    mAdvisoryDisposable = d
                }

                override fun onNext(t: Advisory.AdvisoryList) {
                    if (!mDataSubject.hasValue() || compareValues(mDataSubject.value, t) != 0)
                        mDataSubject.onNext(t)

                    mPageStateSubject.onNext(object: PageStateInfo{
                        override fun GetPageState(): PageStateInfo.PageState {
                            return PageStateInfo.PageState.kData
                        }

                        override fun GetErrorMessage(): String? {
                            return ""
                        }

                    })
                }

                override fun onError(e: Throwable) {
                }
            })
        }
    }

    override fun OnPause() {
        mAdvisoryDisposable?.dispose()
        mAdvisoryDisposable = null
    }

    override fun OnDestroy() {
        mAdvisoryDisposable?.dispose()
        mAdvisoryDisposable = null
        mDataSubject.onComplete()
    }
}
