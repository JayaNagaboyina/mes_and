package org.mesonet.cache.site

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import org.mesonet.models.site.MesonetSiteList

interface SiteCache {
    fun GetSites(inContext: Context): Single<MesonetSiteList>
    fun SaveSites(inContext: Context, inMesonetSites: Map<String, MesonetSiteList.MesonetSite>)
    fun GetFavorites(inContext: Context): Single<MutableList<String>>
    fun SaveFavorites(inContext: Context, inFavorites: List<String>)
}