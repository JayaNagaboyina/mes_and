package org.mesonet.dataprocessing.site.mesonetdata

interface DerivedValues
{
    fun GetApparentTemperature(inTemp: Number?, inWindSpeed: Number?, inHumidity: Number?): Number?
    fun GetDewpoint(inTemperature: Number?, inHumidity: Number?): Number?
    fun GetMSLPressure(inStationPressure: Number?, inElevation: Number?): Number?
}