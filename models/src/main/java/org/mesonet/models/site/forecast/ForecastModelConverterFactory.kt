package org.mesonet.models.site.forecast

import okhttp3.ResponseBody
import org.mesonet.core.DefaultUnits
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.util.ArrayList



class ForecastModelConverterFactory: Converter.Factory() {

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, List<Forecast>>
    {
        if(annotations.any { it is ForecastConverter })
            return ForecastModelConverter()

        return retrofit.nextResponseBodyConverter(this, type, annotations)
    }



    class ForecastModelConverter: Converter<ResponseBody, List<Forecast>> {
        override fun convert(value: ResponseBody?): List<Forecast> {

            val result = ArrayList<ForecastModel>()

            try {
                if (value != null) {
                    val strForecastList = value.string().split("\n")

                    for (i in strForecastList.indices) {

                        val strForecast = strForecastList[i].split(",")

                        if (strForecast.size != 9)
                            continue

                        val forecastModel = ForecastModel()

                        forecastModel.mTime = strForecast[0]
                        forecastModel.mIconUrl = strForecast[1]
                        forecastModel.mStatus = strForecast[2]

                        try {
                            forecastModel.mHighOrLow = Forecast.HighOrLow.valueOf(strForecast[3])
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        }

                        try {
                            forecastModel.mTemp = java.lang.Double.parseDouble(strForecast[4])
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }

                        try {
                            forecastModel.mWindDirectionStart = DefaultUnits.CompassDirections.valueOf(strForecast[5])
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        }

                        try {
                            forecastModel.mWindDirectionEnd = DefaultUnits.CompassDirections.valueOf(strForecast[6])
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        }

                        try {
                            forecastModel.mWindMin = java.lang.Double.parseDouble(strForecast[7])
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }

                        try {
                            forecastModel.mWindMax = java.lang.Double.parseDouble(strForecast[8])
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }

                        if (forecastModel.GetIconUrl() != null ||
                                forecastModel.GetTemp() != null ||
                                forecastModel.GetHighOrLow() != null ||
                                forecastModel.GetStatus() != null ||
                                forecastModel.GetWindDirectionStart() != null ||
                                forecastModel.GetWindDirectionEnd() != null ||
                                forecastModel.GetWindMin() != null ||
                                forecastModel.GetWindMax() != null) {
                            result.add(forecastModel)
                        }
                    }
                }
            }
            finally {
                value?.close()
            }

            return result
        }
    }
}
