package org.mesonet.dataprocessing.site.forecast

import android.content.Context
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.dataprocessing.LifecycleListener
import org.mesonet.dataprocessing.PageStateInfo

interface FiveDayForecastDataController: LifecycleListener
{
    fun GetCount(): Int
    fun GetForecast(inIndex: Int): SemiDayForecastDataController
    fun GetForecastDataSubject(inContext: Context): BehaviorSubject<List<SemiDayForecastDataController>>
    fun GetConnectivityStateObservable(): Observable<Boolean>
    fun GetPageStateObservable(): Observable<PageStateInfo>
}