package org.mesonet.dataprocessing.radar

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.dataprocessing.LifecycleListener
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.models.radar.RadarDetails


interface RadarImageDataProvider: LifecycleListener {
    fun GetSiteInfoObservable(): Observable<Map.Entry<String, RadarDetails>>
    fun GetPageStateObservable(): Observable<PageStateInfo>
    fun GetRadarAnimationObservable(): Observable<List<RadarDataController.ImageInfo>>
}