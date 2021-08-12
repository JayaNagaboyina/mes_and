package org.mesonet.network

import android.graphics.Bitmap
import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.mesonet.models.advisories.Advisory
import org.mesonet.models.advisories.AdvisoryConverter
import org.mesonet.models.advisories.AdvisoryModelConverterFactory
import org.mesonet.models.maps.MapsList
import org.mesonet.models.maps.MapsModel
import org.mesonet.models.radar.*
import org.mesonet.models.site.MesonetSiteList
import org.mesonet.models.site.MesonetSiteListModel
import org.mesonet.models.site.forecast.Forecast
import org.mesonet.models.site.forecast.ForecastConverter
import org.mesonet.models.site.forecast.ForecastModelConverterFactory
import org.mesonet.models.site.mesonetdata.MesonetData
import org.mesonet.models.site.mesonetdata.MesonetDataConverter
import org.mesonet.models.site.mesonetdata.MesonetModelConverterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

import javax.inject.Inject
import javax.inject.Singleton
import com.google.gson.GsonBuilder




@Singleton
class DataProviderImpl @Inject constructor(): DataProvider {
    private val mMesonetService = Retrofit.Builder()
            .baseUrl("http://www.mesonet.org")
            .addConverterFactory(MesonetModelConverterFactory())
            .addConverterFactory(ForecastModelConverterFactory())
            .addConverterFactory(AdvisoryModelConverterFactory())
            .addConverterFactory(RadarHistoryConverterFactory())
            .addConverterFactory(BitmapConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapter(MesonetSiteList.MesonetSite::class.java, MesonetSiteJsonDeserializer()).create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(MesonetService::class.java)

    override fun GetMesonetSites(): Observable<MesonetSiteList> {
        return mMesonetService.GetMesonetSites()
                .map { it as MesonetSiteList }
                .subscribeOn(Schedulers.io())
    }


    override fun GetMesonetData(inStid: String): Observable<MesonetData> {
        return mMesonetService.GetMesonetData(inStid).subscribeOn(Schedulers.computation())
    }


    override fun GetForecast(inStid: String): Observable<Pair<List<Forecast>, String>> {
        return mMesonetService.GetForecast(inStid).map { data ->
            Pair(data, inStid)
        }.subscribeOn(Schedulers.io())
    }


    override fun GetForecastImage(inFileName: String, inSize: String): String {
        return mMesonetService.GetForecastIcon(inFileName, inSize).request().url().toString()
    }


    override fun GetMaps(): Observable<MapsList> {
        return mMesonetService.GetMaps()
                .map { it as MapsList }
                .subscribeOn(Schedulers.io())
    }


    override fun GetRadarHistory(inSiteId: String): Observable<RadarHistory> {
        return mMesonetService.GetRadarHistory(inSiteId).map { it as RadarHistory }.subscribeOn(Schedulers.io())
    }


    override fun GetRadarImage(inSiteId: String,
                      inImageId: String): Observable<Bitmap>
    {
       return mMesonetService.GetRadarImage(inSiteId, inImageId)
    }


    override fun GetAdvisoriesList(): Observable<Advisory.AdvisoryList>
    {
        return mMesonetService.GetAdvisoriesList().subscribeOn(Schedulers.io())
    }


    override fun GetMeteogramImageUrl(inPath: String): String {
        return mMesonetService.GetMeteogram(inPath).request().url().toString()
    }

    override fun GetMapImageUrl(inPath: String): String {
        return inPath + "?" + Date().time.toString()
    }

    override fun GetAdvisoryUrl(inPath: String): String {
        return mMesonetService.GetAdvisory().request().url().toString() + inPath
    }





    interface MesonetService
    {
        @GET("/find/siteinfo-json")
        fun GetMesonetSites(): Observable<MesonetSiteListModel>


        @MesonetDataConverter
        @GET("/index.php/app/latest_iphone/{stid}")
        fun GetMesonetData(@Path("stid") inStid: String): Observable<MesonetData>


        @ForecastConverter
        @GET("/index.php/app/forecast/{stid}")
        fun GetForecast(@Path("stid") inStid: String): Observable<List<Forecast>>


        @GET("/images/fcicons-android/{imageId}{size}.png")
        fun GetForecastIcon(@Path("imageId") inImageId: String, @Path("size") inSize: String): Call<Bitmap>


        @GET("/data/public/mesonet/meteograms/{stid}.met.gif")
        fun GetMeteogram(@Path("stid") inStid: String): Call<Bitmap>


        @GET("http://content.mesonet.org/mesonet/mobile-app/products.json")
        fun GetMaps(): Observable<MapsModel>


        @RadarHistoryConverter
        @GET("/data/nids/maps/realtime/frames_{siteId}_N0Q.xml")
        fun GetRadarHistory(@Path("siteId") inSite: String): Observable<RadarHistoryModel>


        @GET("/data/nids/maps/realtime/storage/{siteId}/N0Q/{imageId}.fullrange.gif")
        fun GetRadarImage(@Path("siteId") inSite: String,
                          @Path("imageId") inImageId: String) : Observable<Bitmap>


        @AdvisoryConverter
        @GET("/data/public/noaa/wwa/mobile-ok.txt")
        fun GetAdvisoriesList(): Observable<Advisory.AdvisoryList>


        @GET("/data/public/noaa/text/archive")
        fun GetAdvisory(): Call<String>
    }
}
