package org.mesonet.dataprocessing.site.mesonetdata

import io.reactivex.Observable
import io.reactivex.ObservableSource
import org.mesonet.core.DefaultUnits
import org.mesonet.dataprocessing.LifecycleListener
import org.mesonet.dataprocessing.site.MesonetSiteDataController
import java.util.*

interface MesonetDataController: LifecycleListener
{
    fun GetDataObservable(): Observable<ProcessedMesonetData>
    fun GetCurrentSiteObservable(): Observable<MesonetSiteDataController.ProcessedMesonetSite>
    fun GetConnectivityStateObservable(): Observable<Boolean>

    interface ProcessedMesonetData{
        fun GetStationName(): String
        fun GetTime(): Date?
        fun GetTemp(): Double?
        fun GetApparentTemp(): Double?
        fun GetDewpoint(): Double?
        fun GetWindSpeed(): Double?
        fun GetWindDirection(): DefaultUnits.CompassDirections?
        fun GetMaxWind(): Double?
        fun Get24HrRain(): Double?
        fun GetHumidity(): Int?
        fun GetPressure(): Double?
    }
}