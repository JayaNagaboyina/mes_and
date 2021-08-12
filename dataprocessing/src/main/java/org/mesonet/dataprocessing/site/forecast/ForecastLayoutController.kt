package org.mesonet.dataprocessing.site.forecast

import android.content.Context
import android.os.Build
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.schedulers.Schedulers
import org.mesonet.dataprocessing.R
import org.mesonet.models.site.forecast.Forecast



class ForecastLayoutController constructor(private var mContext: Context, private var mForecastData: ForecastData)
{
    private var mForecastDataObservable = Observable.create(ObservableOnSubscribe<ForecastDisplayData>{
        val forecastData = ForecastDisplayDataImpl()
        forecastData.mImageUrl = mForecastData.GetIconUrl()

        var colorResource = R.color.colorPrimary

        if (!mForecastData.GetHighOrLowTemp().isEmpty()) {
            val highOrLow = Forecast.HighOrLow.valueOf(mForecastData.GetHighOrLowTemp().split(" ").first())

            colorResource = when (highOrLow) {
                Forecast.HighOrLow.High -> R.color.forecastDayBackground
                Forecast.HighOrLow.Low -> R.color.forecastNightBackground
            }
        }

        forecastData.mForecastData = mForecastData

        forecastData.mBackgroundColor = mContext.resources.getColor(colorResource, mContext.theme)

        it.onNext(forecastData)
    }).subscribeOn(Schedulers.computation())


    fun GetForecastObservable(): Observable<ForecastDisplayData>
    {
        return mForecastDataObservable
    }



    private class ForecastDisplayDataImpl: ForecastDisplayData
    {
        internal var mForecastData: ForecastData? = null
        internal var mBackgroundColor: Int = 0
        internal var mImageUrl: String = ""

        override fun GetBackgroundColor(): Int {
            return mBackgroundColor
        }


        override fun GetData(): ForecastData?
        {
            return mForecastData
        }


        override fun GetImageUrl(): String
        {
            return mImageUrl
        }
    }



    interface ForecastDisplayData
    {
        fun GetBackgroundColor(): Int
        fun GetData(): ForecastData?
        fun GetImageUrl(): String
    }
}