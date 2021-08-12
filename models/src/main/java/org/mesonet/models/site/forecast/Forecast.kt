package org.mesonet.models.site.forecast

import org.mesonet.core.DefaultUnits


interface Forecast : DefaultUnits, Comparable<Forecast>
{
    fun GetTime(): String?
    fun GetTemp(): Number?
    fun GetIconUrl(): String?
    fun GetStatus(): String?
    fun GetHighOrLow(): HighOrLow?
    fun GetWindDirectionStart(): DefaultUnits.CompassDirections?
    fun GetWindDirectionEnd(): DefaultUnits.CompassDirections?
    fun GetWindMin(): Number?
    fun GetWindMax(): Number?


    enum class HighOrLow {
        High, Low;






        fun CompareTo(other: Forecast.HighOrLow?): Int
        {
            if(other == null)
                return 0

            return compareTo(other)
        }
    }
}