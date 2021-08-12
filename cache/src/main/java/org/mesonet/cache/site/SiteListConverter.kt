package org.mesonet.cache.site

import org.mesonet.models.site.MesonetSiteList

interface SiteListConverter
{
    fun SitesToRealmModels(inSiteModels: Map<String, MesonetSiteList.MesonetSite>): List<SiteRealmModel>
    fun SitesFromRealmModels(inRealmModels: List<SiteRealmModel>): MesonetSiteList
    fun FavoritesToRealmModels(inFavorites: List<String>): List<FavoriteSiteRealmModel>
    fun FavoriteFromRealmModels(inRealmModels: List<FavoriteSiteRealmModel>): MutableList<String>
}