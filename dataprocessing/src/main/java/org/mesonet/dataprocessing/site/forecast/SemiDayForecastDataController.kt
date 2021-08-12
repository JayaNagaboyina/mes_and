package org.mesonet.dataprocessing.site.forecast

import android.content.Context
import io.reactivex.*


import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.core.DefaultUnits
import org.mesonet.dataprocessing.PageStateInfo
import java.util.Locale

import org.mesonet.dataprocessing.formulas.UnitConverter
import org.mesonet.dataprocessing.R

import org.mesonet.dataprocessing.userdata.Preferences
import org.mesonet.models.site.forecast.Forecast
import org.mesonet.network.DataProvider


class SemiDayForecastDataController(private var mContext: Context, inPreferences: Preferences, private var mUnitConverter: UnitConverter?, inForecast: Forecast?, val mDataProvider: DataProvider, val mIndex: Int){
    val mDataSubject: BehaviorSubject<Forecast> = BehaviorSubject.create()
    val mPageStateSubject: BehaviorSubject<PageStateInfo> = BehaviorSubject.create()
    val mForecastDataObservable: Observable<ForecastData>

    var mUnitPreferenceDisposable: Disposable? = null



    init {
        mPageStateSubject.onNext(object: PageStateInfo{
            override fun GetPageState(): PageStateInfo.PageState {
                return PageStateInfo.PageState.kLoading
            }

            override fun GetErrorMessage(): String? {
                return ""
            }

        })

        mForecastDataObservable = Observables.combineLatest(mDataSubject,
                inPreferences.UnitPreferencesObservable(mContext))
        {
            data, unitPreference ->

            val result = ForecastDataImpl()
            if (data != null) {
                if (data.GetTime() != null)
                    result.mTime = data.GetTime() ?: ""

                if (mContext.resources?.getBoolean(R.bool.forceWrapForecasts) == true && !result.mTime.contains(" "))
                    result.mTime += "\n"

                if (data.GetIconUrl() != null) {
                    val sizeModifier = mContext.resources?.getString(R.string.forecastImageRes)
                    result.mIconUrl = mDataProvider.GetForecastImage(data.GetIconUrl()?.removePrefix("http://www.nws.noaa.gov/weather/images/fcicons/")?.removeSuffix(".jpg")
                            ?: "", sizeModifier ?: "")
                }

                if (data.GetStatus() != null)
                    result.mStatus = data.GetStatus() + "\n"

                if (data.GetHighOrLow() != null)
                    result.mHighOrLowTemp = data.GetHighOrLow()?.name ?: ""

                var tempUnits: DefaultUnits.TempUnits = DefaultUnits.TempUnits.kCelsius

                if (unitPreference == Preferences.UnitPreference.kImperial)
                    tempUnits = DefaultUnits.TempUnits.kFahrenheit

                var unit = ""

                when (tempUnits) {
                    DefaultUnits.TempUnits.kCelsius ->
                    {
                        unit = "C"
                    }
                    DefaultUnits.TempUnits.kFahrenheit -> {
                        unit = "F"
                    }
                }

                var value = mUnitConverter?.GetTempInPreferredUnits(data.GetTemp(), data, tempUnits)

                result.mHighOrLowTemp += " " + String.format(Locale.getDefault(), "%.0f", value?.toFloat()) + "°" + unit

                if(result.mHighOrLowTemp.startsWith("-0°"))
                    result.mHighOrLowTemp = result.mHighOrLowTemp.replace("-0°", "0°")

                if (data.GetWindMin() == null)
                    result.mWindDescription = "Calm\n"
                else {
                    var speedUnits: DefaultUnits.SpeedUnits = DefaultUnits.SpeedUnits.kMps

                    if (unitPreference == Preferences.UnitPreference.kImperial)
                        speedUnits = DefaultUnits.SpeedUnits.kMph

                    unit = ""

                    when (speedUnits) {
                        DefaultUnits.SpeedUnits.kMps -> unit = "mps"
                        DefaultUnits.SpeedUnits.kMph -> unit = "mph"
                        DefaultUnits.SpeedUnits.kKmph -> unit = "kmph"
                    }

                    if (mUnitConverter == null)
                        result.mWindDescription = ""

                    var directionDesc: String

                    if (data.GetWindDirectionStart() != null) {
                        directionDesc = data.GetWindDirectionStart()?.name ?: ""

                        if (data.GetWindDirectionStart() != data.GetWindDirectionEnd())
                            directionDesc += "-" + data.GetWindDirectionEnd()

                        value = mUnitConverter?.GetSpeedInPreferredUnits(data.GetWindMin(), data, speedUnits)?.toInt()
                        val value2 = mUnitConverter?.GetSpeedInPreferredUnits(data.GetWindMax(), data, speedUnits)?.toInt()

                        var finalWindSpdValue = value.toString()
                        if (value != value2)
                            finalWindSpdValue += "-$value2"

                        result.mWindDescription = "Wind $directionDesc $finalWindSpdValue $unit"
                    }
                }
            }

            mPageStateSubject.onNext(object: PageStateInfo{
                override fun GetPageState(): PageStateInfo.PageState {
                    return PageStateInfo.PageState.kData
                }

                override fun GetErrorMessage(): String? {
                    return ""
                }

            })

            result as ForecastData
        }.subscribeOn(Schedulers.computation())

        if(inForecast != null)
            mDataSubject.onNext(inForecast)
    }


    fun GetForecastDataObservable(): Observable<ForecastData>
    {
        return mForecastDataObservable
    }

    fun StartReloading()
    {
        mPageStateSubject.onNext(object: PageStateInfo{
            override fun GetPageState(): PageStateInfo.PageState {
                return PageStateInfo.PageState.kLoading
            }

            override fun GetErrorMessage(): String? {
                return ""
            }

        })
    }


    fun SetEmpty()
    {
        mPageStateSubject.onNext(object: PageStateInfo{
            override fun GetPageState(): PageStateInfo.PageState {
                return PageStateInfo.PageState.kError
            }

            override fun GetErrorMessage(): String? {
                return ""
            }

        })
    }


    fun GetPageStateObservable(): Observable<PageStateInfo>
    {
        return mPageStateSubject
    }


    fun SetData(inForecast: Forecast?)
    {
        if(inForecast != null)
            mDataSubject.onNext(inForecast)
    }


    fun Dispose(){
        mUnitPreferenceDisposable?.dispose()
        mUnitPreferenceDisposable = null
        mDataSubject.onComplete()
        mPageStateSubject.onComplete()
    }

    class ForecastDataImpl(internal var mTime: String = "",
                           internal var mIconUrl: String = "",
                           internal var mStatus: String = "",
                           internal var mHighOrLowTemp: String = "",
                           internal var mWindDescription: String = ""): ForecastData {

        override fun compareTo(other: ForecastData): Int {
            var result = GetTime().compareTo(other.GetTime())

            if(result == 0)
                result = GetIconUrl().compareTo(other.GetIconUrl())

            if(result == 0)
                result = GetStatus().compareTo(other.GetStatus())

            if(result == 0)
                result = GetHighOrLowTemp().compareTo(other.GetHighOrLowTemp())

            if(result == 0)
                result = GetWindDescription().compareTo(other.GetWindDescription())

            return result
        }

        override fun GetTime(): String {
            return mTime
        }

        override fun GetIconUrl(): String {
            return mIconUrl
        }

        override fun GetStatus(): String {
            return mStatus
        }

        override fun GetHighOrLowTemp(): String {
            return mHighOrLowTemp
        }


        override fun GetWindDescription(): String {
            return mWindDescription
        }
    }
}
