package org.mesonet.models.site.mesonetdata

import org.mesonet.core.DefaultUnits

interface MesonetData : DefaultUnits, Comparable<MesonetData> {
    fun GetTime(): Long?
    fun GetRelH(): Number?
    fun GetTAir(): Number?
    fun GetWSpd(): Number?
    fun GetWDir(): Number?
    fun GetWMax(): Number?
    fun GetPres(): Number?
    fun GetRain24H(): Number?
    fun GetStID(): String?
}