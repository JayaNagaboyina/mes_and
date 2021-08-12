package org.mesonet.dataprocessing.site

import io.reactivex.Observable
import org.mesonet.dataprocessing.LifecycleListener
import org.mesonet.dataprocessing.SelectSiteListener
import org.mesonet.dataprocessing.filterlist.FilterListDataProvider
import org.mesonet.models.site.MesonetSiteList


interface MesonetSiteDataController: FilterListDataProvider, SelectSiteListener, LifecycleListener
{
    fun GetCurrentSelectionObservable(): Observable<ProcessedMesonetSite>
    fun ToggleFavorite(inStid: String): Observable<Boolean>

    interface ProcessedMesonetSite : MesonetSiteList.MesonetSite, Comparable<ProcessedMesonetSite> {
        fun GetStid(): String
        fun IsFavorite(): Boolean
        fun GetMeteogramUrl(): String
    }
}