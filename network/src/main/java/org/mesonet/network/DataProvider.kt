package org.mesonet.network

import android.graphics.Bitmap
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.schedulers.Schedulers
import org.mesonet.models.advisories.Advisory
import org.mesonet.models.maps.MapsList
import org.mesonet.models.radar.RadarHistory
import org.mesonet.models.site.MesonetSiteList
import org.mesonet.models.site.forecast.Forecast
import org.mesonet.models.site.mesonetdata.MesonetData

interface DataProvider
{
    fun GetMesonetSites(): Observable<MesonetSiteList>
    fun GetMesonetData(inStid: String): Observable<MesonetData>
    fun GetMeteogramImageUrl(inPath: String): String
    fun GetForecast(inStid: String): Observable<Pair<List<Forecast>, String>>
    fun GetForecastImage(inFileName: String, inSize: String): String
    fun GetMaps(): Observable<MapsList>
    fun GetMapImageUrl(inPath: String): String
    fun GetRadarHistory(inSiteId: String): Observable<RadarHistory>
    fun GetRadarImage(inSiteId: String, inImageId: String): Observable<Bitmap>
    fun GetAdvisoriesList(): Observable<Advisory.AdvisoryList>
    fun GetAdvisoryUrl(inPath: String): String
}