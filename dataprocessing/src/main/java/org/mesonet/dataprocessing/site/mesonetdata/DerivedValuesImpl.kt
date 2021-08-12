package org.mesonet.dataprocessing.site.mesonetdata


import org.mesonet.dataprocessing.formulas.UnitConverter
import java.lang.Math.pow
import javax.inject.Inject

class DerivedValuesImpl @Inject
constructor(): DerivedValues {


    @Inject
    internal lateinit var mUnitConverter: UnitConverter


    internal fun GetHeatIndex(inTemp: Number?, inHumidity: Number?): Number? {
        if (inTemp == null || inHumidity == null || inTemp.toDouble() < -900.0 || inHumidity.toDouble() < -900.0)
            return null

        val fahrenheit = mUnitConverter.CelsiusToFahrenheit(inTemp)

        return if (fahrenheit?.toDouble()?: 0.0 < kHeatIndexTempThreshold) null else mUnitConverter.FahrenheitToCelsius(-42.379 +
                2.04901523 * (fahrenheit?.toDouble()?: 0.0) +
                10.14333127 * inHumidity.toDouble() -
                0.22475541 * (fahrenheit?.toDouble()?: 0.0) * inHumidity.toDouble() -
                0.00683783 * Math.pow((fahrenheit?.toDouble()?: 0.0), 2.0) -
                0.05481717 * Math.pow(inHumidity.toDouble(), 2.0) +
                0.00122874 * Math.pow((fahrenheit?.toDouble()?: 0.0), 2.0) * inHumidity.toDouble() +
                0.00085282 * (fahrenheit?.toDouble()?: 0.0) * Math.pow(inHumidity.toDouble(), 2.0) - 0.00000199 * Math.pow(fahrenheit?.toDouble()?: 0.0, 2.0) * Math.pow(inHumidity.toDouble(), 2.0))

    }


    internal fun GetWindChill(inTemp: Number?, inWindSpeed: Number?): Number? {
        if (inTemp == null || inWindSpeed == null || inTemp.toDouble() < -900.0 || inWindSpeed.toDouble() < -900.0)
            return null

        val fahrenheit = mUnitConverter.CelsiusToFahrenheit(inTemp)
        val mph = mUnitConverter.MpsToMph(inWindSpeed)

        if (fahrenheit?.toDouble()?: 0.0 > kWindChillTempThreshold)
            return null

        if(mph?.toDouble()?: 0.0 < 3.0)
            return inTemp

        val windSpeedFunction = Math.pow(mph?.toDouble()?: 0.0, 0.16)

        return mUnitConverter.FahrenheitToCelsius(35.74 + 0.6215 * (fahrenheit?.toDouble()?: 0.0) - 35.75 * windSpeedFunction + 0.4275 * (fahrenheit?.toDouble()?: 0.0) * windSpeedFunction)
    }


    override fun GetApparentTemperature(inTemp: Number?, inWindSpeed: Number?, inHumidity: Number?): Number? {
        if (inTemp == null)
            return null

        val fahrenheit = mUnitConverter.CelsiusToFahrenheit(inTemp)

        if (fahrenheit?.toDouble()?: 0.0 <= kWindChillTempThreshold) {
            if (inWindSpeed == null)
                return null

            return GetWindChill(inTemp, inWindSpeed)
        }

        if (fahrenheit?.toDouble()?: 0.0 >= kHeatIndexTempThreshold) {
            if (inHumidity == null)
                return null

            return GetHeatIndex(inTemp, inHumidity)
        }

        return inTemp
    }


    override fun GetDewpoint(inTemperature: Number?, inHumidity: Number?): Number? {
        if (inTemperature == null || inHumidity == null)
            return null

        val saturatedPressure = 6.11 * Math.pow(10.0, 7.5 * inTemperature.toDouble() / (237.3 + inTemperature.toDouble()))

        return 237.3 * Math.log(saturatedPressure * inHumidity.toDouble() / 611.0) / (7.5 * Math.log(10.0) - Math.log(saturatedPressure * inHumidity.toDouble() / 611.0))
    }


    override fun GetMSLPressure(inStationPressure: Number?, inElevation: Number?): Number? {
        val kGamma = 6.5		// Lapse rate for standard atmosphere.
        val kMTAIR = 288.0		// Mean surface temperature for standard atmosphere.
        val kMPSL = 1013.25		// Mean surface pressure for standard atmosphere.
        val kC1 = 0.1901631		// C1 = gamma Rd/g.
        val kC2 = 5.258643		// C2 = g/gamma Rd.

        val elev = (inElevation?: return null).toDouble() / 1000.0
        return (inStationPressure?: return null).toDouble() * pow(1 + pow((kMPSL / inStationPressure.toDouble()), kC1) * (kGamma * elev / kMTAIR), kC2)
    }


    companion object {
        private val kWindChillTempThreshold = 50.0
        private val kHeatIndexTempThreshold = 80.0
    }
}