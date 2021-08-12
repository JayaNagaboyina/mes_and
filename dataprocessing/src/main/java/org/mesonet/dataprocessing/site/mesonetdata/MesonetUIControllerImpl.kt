package org.mesonet.dataprocessing.site.mesonetdata

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.rxkotlin.Observables
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.core.PerContext
import org.mesonet.dataprocessing.ConnectivityStatusProvider
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.dataprocessing.site.MesonetSiteDataController
import org.mesonet.dataprocessing.userdata.Preferences

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

import javax.inject.Inject


@PerContext
class MesonetUIControllerImpl @Inject
constructor(private var mDataController: MesonetDataController,
            private var mPreferences: Preferences,
            private var mMesonetSiteDataController: MesonetSiteDataController,
            private var mConnectivityStatusProvider: ConnectivityStatusProvider): MesonetUIController
{
    private lateinit var mUIObservable: Observable<MesonetUIController.MesonetDisplayFields>
    private var mPageStateSubject: BehaviorSubject<PageStateInfo> = BehaviorSubject.create()

    private var mUnitPreferenceDisposable: Disposable? = null
    private var mConnectivityDisposable: Disposable? = null
    private var mCurrentSelectionDisposable: Disposable? = null

    private var mLastSiteProcessed = ""
    private var mLastSiteSelected = ""

    private var mCreated = false


    override fun OnCreate(inContext: Context)
    {
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
                mLastSiteSelected = t.GetStid()

                if(mLastSiteSelected == mLastSiteProcessed) {
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
                    if(mLastSiteSelected == mLastSiteProcessed && mLastSiteSelected.isNotBlank()) {
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


        mDataController.OnCreate(inContext)

        synchronized(this)
        {
            if (!mCreated) {

                mCreated = true
            }
        }
    }


    override fun GetPageStateObservable(): Observable<PageStateInfo> {
        return mPageStateSubject
    }


    override fun GetDisplayFieldsObservable(): Observable<MesonetUIController.MesonetDisplayFields>
    {
        return mUIObservable
    }


    override fun OnResume(inContext: Context) {
        mDataController.OnResume(inContext)

        mUIObservable = Observables.combineLatest(mDataController.GetDataObservable().distinctUntilChanged(),
                mDataController.GetCurrentSiteObservable().distinctUntilChanged { site -> site.GetStid() },
                mPreferences.UnitPreferencesObservable(inContext).distinctUntilChanged(),
                mConnectivityStatusProvider.ConnectivityStatusObservable()) { data, site, unitPreference, connectivityStatus ->
            val displayFields = MesonetDisplayFieldsImpl()
            val formattedString = "Observed at %s"

            displayFields.mStationName = site.GetName() ?: ""
            displayFields.mIsFavorite = site.IsFavorite()

            val time = data.GetTime()

            if (time == null)
                displayFields.mTimeString = String.format(formattedString, "-:-")
            else {
                val dateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
                dateFormat.timeZone = TimeZone.getTimeZone("America/Chicago")

                displayFields.mTimeString = String.format(formattedString, dateFormat.format(time))
            }

            val temp = data.GetTemp()

            if (temp == null)
                displayFields.mAirTempString = "-"
            else
                displayFields.mAirTempString = String.format(Locale.getDefault(), "%.0f", temp) + "°"

            if(displayFields.mAirTempString.startsWith("-0°"))
                displayFields.mAirTempString = displayFields.mAirTempString.replace("-0°", "0°")

            val apparentTemp = data.GetApparentTemp()

            if (apparentTemp == null)
                displayFields.mApparentTempString = "-"
            else {
                var unit = ""

                when (unitPreference) {
                    Preferences.UnitPreference.kMetric -> {
                        unit = "C"
                    }
                    Preferences.UnitPreference.kImperial -> {
                        unit = "F"
                    }
                    null -> {
                        unit = "F"
                    }
                }

                displayFields.mApparentTempString = String.format(Locale.getDefault(), "%.0f", apparentTemp) + "°" + unit

                if(displayFields.mApparentTempString.startsWith("-0°"))
                    displayFields.mApparentTempString = displayFields.mApparentTempString.replace("-0°", "0°")
            }

            val dewPoint = data.GetDewpoint()

            if (dewPoint == null)
                displayFields.mDewPointString = "-"
            else {
                var unit = ""

                when (unitPreference) {
                    null -> {
                        unit = "F"
                    }
                    Preferences.UnitPreference.kMetric -> {
                        unit = "C"
                    }
                    Preferences.UnitPreference.kImperial -> {
                        unit = "F"
                    }
                }

                displayFields.mDewPointString = String.format(Locale.getDefault(), "%.0f", dewPoint) + "°" + unit

                if(displayFields.mDewPointString.startsWith("-0°"))
                    displayFields.mDewPointString = displayFields.mDewPointString.replace("-0°", "0°")
            }

            val windSpd = data.GetWindSpeed()
            val direction = data.GetWindDirection()

            if (windSpd == null || direction == null)
                displayFields.mWindString = "-"
            else {
                var unit = ""

                when (unitPreference) {
                    Preferences.UnitPreference.kMetric -> unit = "mps"
                    Preferences.UnitPreference.kImperial -> unit = "mph"
                    null -> unit = "mph"
                }

                displayFields.mWindString = direction.name + " at " + String.format(Locale.getDefault(), "%.0f", windSpd) + " " + unit
            }

            val rain24h = data.Get24HrRain()

            if (rain24h == null)
                displayFields.m24HrRainfallString = "-"
            else {
                var unit = ""

                when (unitPreference) {
                    Preferences.UnitPreference.kMetric -> unit = "mm"
                    Preferences.UnitPreference.kImperial -> unit = "in"
                    null -> unit = "in"
                }

                displayFields.m24HrRainfallString = String.format(Locale.getDefault(), "%.2f", rain24h) + " " + unit
            }

            val humidity = data.GetHumidity()

            if (humidity == null)
                displayFields.mHumidityString = "-"
            else
                displayFields.mHumidityString = humidity.toString() + "%"

            val windGusts = data.GetMaxWind()

            if (windGusts == null)
                displayFields.mWindGustsString = "-"
            else {
                var unit = ""

                when (unitPreference) {
                    Preferences.UnitPreference.kMetric -> unit = "mps"
                    Preferences.UnitPreference.kImperial -> unit = "mph"
                    null -> unit = "mph"
                }

                displayFields.mWindGustsString = String.format(Locale.getDefault(), "%.0f", windGusts) + " " + unit
            }

            val pressure = data.GetPressure()

            if (pressure == null)
                displayFields.mPressureString = "-"
            else {
                var unit = ""
                var format = ""

                when (unitPreference) {
                    Preferences.UnitPreference.kMetric -> {
                        unit = "mb"
                        format = "%.1f"
                    }
                    Preferences.UnitPreference.kImperial -> {
                        unit = "inHg"
                        format = "%.2f"
                    }
                    null -> {
                        unit = "inHg"
                        format = "%.2f"
                    }
                }

                displayFields.mPressureString = String.format(Locale.getDefault(), format, pressure) + " " + unit
            }

            if(site.GetName() == data.GetStationName()) {
                mPageStateSubject.onNext(object : PageStateInfo {
                    override fun GetPageState(): PageStateInfo.PageState {
                        return PageStateInfo.PageState.kData
                    }

                    override fun GetErrorMessage(): String? {
                        return null
                    }

                })
            }
            else if(connectivityStatus)
            {
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
                mPageStateSubject.onNext(object : PageStateInfo {
                    override fun GetPageState(): PageStateInfo.PageState {
                        return PageStateInfo.PageState.kError
                    }

                    override fun GetErrorMessage(): String? {
                        return "No Connection"
                    }

                })
            }

            mLastSiteProcessed = site.GetStid()

            displayFields as MesonetUIController.MesonetDisplayFields
        }.subscribeOn(Schedulers.computation())
    }

    override fun OnPause() {
        mDataController.OnPause()
    }

    override fun OnDestroy() {
        mUnitPreferenceDisposable?.dispose()
        mUnitPreferenceDisposable = null
        mCurrentSelectionDisposable?.dispose()
        mCurrentSelectionDisposable = null
        mConnectivityDisposable?.dispose()
        mConnectivityDisposable = null
        mDataController.OnDestroy()
        mCreated = false
    }


    private class MesonetDisplayFieldsImpl(internal var mStationName: String = "",
                                           internal var mTimeString: String = "Observed at -:-",
                                           internal var mAirTempString: String = "-",
                                           internal var mApparentTempString: String = "-",
                                           internal var mDewPointString: String = "-",
                                           internal var mWindString: String = "-",
                                           internal var m24HrRainfallString: String = "-",
                                           internal var mHumidityString: String = "-",
                                           internal var mWindGustsString: String = "-",
                                           internal var mPressureString: String = "-",
                                           internal var mIsFavorite: Boolean = false) : MesonetUIController.MesonetDisplayFields {
        override fun GetStationName(): String {
            return mStationName
        }

        override fun GetAirTempString(): String {
            return mAirTempString
        }


        override fun GetApparentTempString(): String {
            return mApparentTempString
        }


        override fun GetDewpointString(): String {
            return mDewPointString
        }


        override fun GetWindString(): String {
            return mWindString
        }


        override fun Get24HrRainfallString(): String {
            return m24HrRainfallString
        }


        override fun GetHumidityString(): String {
            return mHumidityString
        }


        override fun GetWindGustsString(): String {
            return mWindGustsString
        }


        override fun GetPressureString(): String {
            return mPressureString
        }


        override fun GetTimeString(): String {
            return mTimeString
        }

        override fun IsFavorite(): Boolean {
            return mIsFavorite
        }
    }
}