package org.mesonet.dataprocessing.maps

import io.reactivex.Observable
import org.mesonet.dataprocessing.LifecycleListener
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.models.maps.MapsList

interface MapsDataProvider: LifecycleListener {

    fun GetMapsListObservable(): Observable<MapsList?>
    fun GetPageStateObservable(): Observable<PageStateInfo>

    fun GetSections(inSectionIds: List<String>): Observable<LinkedHashMap<String, MapsList.GroupSection>>
    fun GetProducts(inProductIds: List<String>): Observable<LinkedHashMap<String, MapsList.Product>>
}