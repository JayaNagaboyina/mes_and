package org.mesonet.cache.site


import org.mesonet.models.site.MesonetSiteList
import org.mesonet.models.site.MesonetSiteListModel

import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SiteListConverterImpl @Inject
constructor(): SiteListConverter {


    override fun SitesToRealmModels(inSiteModels: Map<String, MesonetSiteList.MesonetSite>): List<SiteRealmModel> {
        val result = ArrayList<SiteRealmModel>()

        val keySet = ArrayList(inSiteModels.keys)

        for (i in 0 until inSiteModels.size) {
            try {
                val realmModel = inSiteModels[keySet[i]]?.let { SiteRealmModel(keySet[i], it) }

                if(realmModel != null)
                    result.add(realmModel)
            }
            finally {

            }
        }

        return result
    }


    override fun SitesFromRealmModels(inRealmModels: List<SiteRealmModel>): MesonetSiteList {
        val result = MesonetSiteListModel()

        for (i in inRealmModels.indices) {
            result[inRealmModels[i].GetStid().toString()] = inRealmModels[i].ToSiteModel()
        }

        return result
    }


    override fun FavoritesToRealmModels(inFavorites: List<String>): List<FavoriteSiteRealmModel> {
        val result = ArrayList<FavoriteSiteRealmModel>()

        for (i in inFavorites.indices) {
            result.add(FavoriteSiteRealmModel(inFavorites[i]))
        }

        return result
    }


    override fun FavoriteFromRealmModels(inRealmModels: List<FavoriteSiteRealmModel>): MutableList<String> {
        val result = ArrayList<String>()

        for (i in inRealmModels.indices) {
            result.add(inRealmModels[i].GetName().toString())
        }

        return result
    }
}
