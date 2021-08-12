package org.mesonet.dataprocessing.site.mesonetdata

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.core.DefaultUnits
import org.mesonet.core.PerContext
import org.mesonet.dataprocessing.ConnectivityStatusProvider
import org.mesonet.dataprocessing.formulas.UnitConverter
import org.mesonet.dataprocessing.site.MesonetSiteDataController
import org.mesonet.dataprocessing.userdata.Preferences
import org.mesonet.models.site.mesonetdata.MesonetData
import org.mesonet.network.DataProvider
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong

import javax.inject.Inject


@PerContext
class MesonetDataControllerImpl @Inject constructor(private var mSiteDataController: MesonetSiteDataController,
                                                    private var mDerivedValues: DerivedValues,
                                                    private var mUnitConverter: UnitConverter,
                                                    private var mDataProvider: DataProvider,
                                                    private var mPreferences: Preferences,
                                                    private var mConnectivityStatusProvider: ConnectivityStatusProvider) : MesonetDataController


{
    private var mCurrentSiteDisposable: Disposable? = null

    private var mLastStidUpdated = ""

    private var mLastTickUpdated = -1L
    private var mUpdateLock = Object()

    private var mComplete = AtomicBoolean(false)

    private var mUpdateDisposable: Disposable? = null

    private var mUpdateObservable: Observable<MesonetData>? = null
    private lateinit var mTickObservable: Observable<Long>
    private lateinit var mProcessObservable: Observable<MesonetDataController.ProcessedMesonetData>
    private val mConnectivityObservable = mConnectivityStatusProvider.ConnectivityStatusObservable()

    private val mMesonetDataSubject: BehaviorSubject<MesonetData> = BehaviorSubject.create()

    private var mLastProcessedData: MesonetDataController.ProcessedMesonetData? = null

    override fun OnCreate(inContext: Context) {
        mSiteDataController.OnCreate(inContext)

        mProcessObservable = Observables.combineLatest(mSiteDataController.GetCurrentSelectionObservable(),
                mMesonetDataSubject,
                mPreferences.UnitPreferencesObservable(inContext).repeatUntil { mComplete.get() }.distinctUntilChanged { unitPreference -> unitPreference.ordinal })
        { site, data, unitPreference ->

            if(site.GetStid().toLowerCase() == data.GetStID()?.toLowerCase())
                mLastProcessedData = ProcessedMesonetDataImpl(site.GetName() ?: "",
                        ProcessTime(data),
                        ProcessTemp(data, unitPreference),
                        ProcessApparentTemp(data, unitPreference),
                        ProcessDewpoint(data, unitPreference),
                        ProcessWindSpd(data, unitPreference),
                        ProcessWindDirection(data),
                        ProcessMaxWind(data, unitPreference),
                        Process24HrRain(data, unitPreference),
                        ProcessHumidity(data),
                        ProcessPressure(data, site.GetElev()
                                ?: "", unitPreference))

            mLastProcessedData?: ProcessedMesonetDataImpl("", Date(), 0.0, 0.0, 0.0,0.0,DefaultUnits.CompassDirections.N, 0.0, 0.0,0, 0.0)
        }
    }


    override fun GetConnectivityStateObservable(): Observable<Boolean> {
        return mConnectivityObservable.takeWhile{!mMesonetDataSubject.hasValue() || mMesonetDataSubject.value?.GetStID() != mSiteDataController.CurrentSelection()}.retryWhen { mSiteDataController.GetCurrentSelectionObservable().distinctUntilChanged{ site -> site.GetStid() } }.retryWhen { mConnectivityObservable.distinctUntilChanged{status -> status} }
    }

    override fun OnResume(inContext: Context) {
        synchronized(mUpdateLock)
        {
            if(mUpdateDisposable?.isDisposed != false) {
                mSiteDataController.OnResume(inContext)

                if(mUpdateObservable == null) {
                    mTickObservable = Observable.interval(0, 1, TimeUnit.MINUTES).distinctUntilChanged().retryWhen { mSiteDataController.GetCurrentSelectionObservable() }.retryWhen { mConnectivityObservable }
                    mUpdateObservable = Observables.combineLatest(mTickObservable,
                            mConnectivityObservable,
                            mSiteDataController.GetCurrentSelectionObservable().distinctUntilChanged { site -> site.GetStid() })
                    { _, connectivity, site ->
                        site
                    }.retryWhen { mSiteDataController.GetCurrentSelectionObservable() }.retryWhen { mConnectivityObservable }.retryWhen { mTickObservable }.flatMap { site ->
                        mDataProvider.GetMesonetData(site.GetStid()).retryWhen { mSiteDataController.GetCurrentSelectionObservable() }.retryWhen { mConnectivityObservable }.retryWhen { mTickObservable }
                    }.retryWhen { mSiteDataController.GetCurrentSelectionObservable() }.retryWhen { mConnectivityObservable }.retryWhen { mTickObservable }.subscribeOn(Schedulers.computation())
                }

                mUpdateObservable?.observeOn(Schedulers.computation())?.subscribe(object : Observer<MesonetData> {
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {
                        mUpdateDisposable?.dispose()
                        mUpdateDisposable = d
                    }

                    override fun onNext(t: MesonetData) {
                        mMesonetDataSubject.onNext(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
            }
        }
    }

    override fun OnPause() {
        mUpdateDisposable?.dispose()
        mUpdateDisposable = null

    }

    override fun OnDestroy() {
        mCurrentSiteDisposable?.dispose()
        mCurrentSiteDisposable = null

        mMesonetDataSubject.onComplete()
    }


    private fun ProcessTemp(inMesonetData: MesonetData, inUnitPreference: Preferences.UnitPreference): Double? {
        if (inMesonetData.GetTAir()?.toDouble()?: -900.0 <= -900.0)
            return null

        var tempUnits = DefaultUnits.TempUnits.kCelsius

        if (inUnitPreference == Preferences.UnitPreference.kImperial)
            tempUnits = DefaultUnits.TempUnits.kFahrenheit

        val result = mUnitConverter.GetTempInPreferredUnits(inMesonetData.GetTAir(), inMesonetData, tempUnits)
                ?: return null

        return result.toDouble()
    }


    private fun ProcessApparentTemp(inMesonetData: MesonetData, inUnitPreference: Preferences.UnitPreference): Double? {
        if (inMesonetData.GetTAir()?.toDouble()?: -900.0 <= -900.0)
            return null

        var tempUnits = DefaultUnits.TempUnits.kCelsius

        if (inUnitPreference == Preferences.UnitPreference.kImperial)
            tempUnits = DefaultUnits.TempUnits.kFahrenheit

        val apparentTemp = mDerivedValues.GetApparentTemperature(inMesonetData.GetTAir(), inMesonetData.GetWSpd(), inMesonetData.GetRelH())

        val result = mUnitConverter.GetTempInPreferredUnits(apparentTemp, inMesonetData, tempUnits)
                ?: return null

        return result.toDouble()
    }



    private fun ProcessDewpoint(inMesonetData: MesonetData, inUnitPreference: Preferences.UnitPreference): Double? {
        if (inMesonetData.GetTAir()?.toDouble()?: -900.0 <= -900.0 || inMesonetData.GetRelH()?.toDouble()?: -900.0 < -900.0)
            return null

        var tempUnits = DefaultUnits.TempUnits.kCelsius

        if (inUnitPreference == Preferences.UnitPreference.kImperial)
            tempUnits = DefaultUnits.TempUnits.kFahrenheit

        val apparentTemp = mDerivedValues.GetDewpoint(inMesonetData.GetTAir(), inMesonetData.GetRelH())

        val result = mUnitConverter.GetTempInPreferredUnits(apparentTemp, inMesonetData, tempUnits)
                ?: return null

        return result.toDouble()
    }


    private fun ProcessWindSpd(inMesonetData: MesonetData, inUnitPreference: Preferences.UnitPreference): Double? {
        if (inMesonetData.GetWSpd() == null)
            return null

        if (inMesonetData.GetWSpd()?.toDouble()?: -900.0 <= -900.0)
            return null

        var speedUnits = DefaultUnits.SpeedUnits.kMps

        if (inUnitPreference == Preferences.UnitPreference.kImperial)
            speedUnits = DefaultUnits.SpeedUnits.kMph

        val result = mUnitConverter.GetSpeedInPreferredUnits(inMesonetData.GetWSpd(), inMesonetData, speedUnits)
                ?: return null

        return result.toDouble()
    }


    private fun ProcessWindDirection(inMesonetData: MesonetData): DefaultUnits.CompassDirections? {
        return if (inMesonetData.GetWDir()?.toDouble()?: -900.0 <= -900.0) null else mUnitConverter.GetCompassDirection(inMesonetData.GetWDir())
    }


    private fun ProcessMaxWind(inMesonetData: MesonetData, inUnitPreference: Preferences.UnitPreference): Double? {
        if (inMesonetData.GetWMax()?.toDouble()?: -900.0 <= -900.0)
            return null

        var speedUnits = DefaultUnits.SpeedUnits.kMps

        if (inUnitPreference == Preferences.UnitPreference.kImperial)
            speedUnits = DefaultUnits.SpeedUnits.kMph

        val result = mUnitConverter.GetSpeedInPreferredUnits(inMesonetData.GetWMax(), inMesonetData, speedUnits)
                ?: return null

        return result.toDouble()
    }


    private fun Process24HrRain(inMesonetData: MesonetData, inUnitPreference: Preferences.UnitPreference): Double? {
        if (inMesonetData.GetRain24H() == null)
            return null

        if (inMesonetData.GetRain24H()?.toDouble()?: -900.0 <= -900.0)
            return null

        var lengthUnits = DefaultUnits.LengthUnits.kMm

        if (inUnitPreference == Preferences.UnitPreference.kImperial)
            lengthUnits = DefaultUnits.LengthUnits.kIn

        val result = mUnitConverter.GetLengthInPreferredUnits(inMesonetData.GetRain24H(), inMesonetData, lengthUnits)
                ?: return null

        return result.toDouble()
    }


    private fun ProcessHumidity(inMesonetData: MesonetData): Int? {
        return if (inMesonetData.GetRelH() == null || inMesonetData.GetRelH()?.toDouble()?: -900.0 < -900.0) null else inMesonetData.GetRelH()?.toInt()
    }


    private fun ProcessPressure(inMesonetData: MesonetData, inElevation: String, inUnitPreference: Preferences.UnitPreference): Double? {
        val elev = inElevation.toDoubleOrNull()
        if (inMesonetData.GetPres()?.toDouble()?: -900.0 <= -900.0 || elev == null)
            return null

        var pressureUnits = DefaultUnits.PressureUnits.kMb

        if (inUnitPreference == Preferences.UnitPreference.kImperial)
            pressureUnits = DefaultUnits.PressureUnits.kInHg

        val result = mUnitConverter.GetPressureInPreferredUnits(mDerivedValues.GetMSLPressure(inMesonetData.GetPres(), elev), inMesonetData, pressureUnits)
                ?: return null

        return result.toDouble()
    }


    override fun GetDataObservable(): Observable<MesonetDataController.ProcessedMesonetData>
    {
        return mProcessObservable
    }


    override fun GetCurrentSiteObservable(): Observable<MesonetSiteDataController.ProcessedMesonetSite>
    {
        return mSiteDataController.GetCurrentSelectionObservable()
    }



    private fun ProcessTime(inMesonetData: MesonetData): java.util.Date? {
        return if (inMesonetData.GetTime() == null) null else java.util.Date((inMesonetData.GetTime()?: 0) * 1000)
    }


    private class ProcessedMesonetDataImpl(private var mStationName: String,
                                           private var mTime: java.util.Date?,
                                           private var mTemp: Double?,
                                           private var mApparentTemp: Double?,
                                           private var mDewpoint: Double?,
                                           private var mWindSpeed: Double?,
                                           private var mWindDirection: DefaultUnits.CompassDirections?,
                                           private var mMaxWind: Double?,
                                           private var m24HrRain: Double?,
                                           private var mHumidity: Int?,
                                           private var mPressure: Double?): MesonetDataController.ProcessedMesonetData {
        override fun GetStationName(): String {
            return mStationName
        }

        override fun GetTime(): java.util.Date? {
            return mTime
        }

        override fun GetTemp(): Double? {
            return mTemp
        }

        override fun GetApparentTemp(): Double? {
            return mApparentTemp
        }

        override fun GetDewpoint(): Double? {
            return mDewpoint
        }

        override fun GetWindSpeed(): Double? {
            return mWindSpeed
        }

        override fun GetWindDirection(): DefaultUnits.CompassDirections? {
            return mWindDirection
        }

        override fun GetMaxWind(): Double? {
            return mMaxWind
        }

        override fun Get24HrRain(): Double? {
            return m24HrRain
        }

        override fun GetHumidity(): Int? {
            return mHumidity
        }

        override fun GetPressure(): Double? {
            return mPressure
        }

    }


}
