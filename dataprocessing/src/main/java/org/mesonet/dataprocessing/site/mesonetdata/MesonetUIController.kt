package org.mesonet.dataprocessing.site.mesonetdata

import io.reactivex.Observable
import org.mesonet.dataprocessing.LifecycleListener
import org.mesonet.dataprocessing.PageStateInfo


interface MesonetUIController: LifecycleListener
{
    fun GetDisplayFieldsObservable(): Observable<MesonetDisplayFields>
    fun GetPageStateObservable(): Observable<PageStateInfo>

    interface MesonetDisplayFields
    {
        fun GetStationName(): String
        fun GetAirTempString(): String
        fun GetApparentTempString(): String
        fun GetDewpointString(): String
        fun GetWindString(): String
        fun Get24HrRainfallString(): String
        fun GetHumidityString(): String
        fun GetWindGustsString(): String
        fun GetPressureString(): String
        fun GetTimeString(): String
        fun IsFavorite(): Boolean
    }


}