package org.mesonet.dataprocessing.site.forecast


interface ForecastData: Comparable<ForecastData> {
    fun GetTime(): String
    fun GetIconUrl(): String
    fun GetStatus(): String
    fun GetHighOrLowTemp(): String
    fun GetWindDescription(): String
}