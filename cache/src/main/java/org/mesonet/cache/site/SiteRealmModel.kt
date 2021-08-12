package org.mesonet.cache.site


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.mesonet.models.site.MesonetSiteList
import org.mesonet.models.site.MesonetSiteListModel


open class SiteRealmModel : RealmObject {
    @PrimaryKey
    private var mStid: String? = null
    private var mLat: String? = null
    private var mElev: String? = null
    private var mStnm: String? = null
    private var mName: String? = null
    private var mLon: String? = null


    constructor()


    constructor(inStid: String, inSiteModel: MesonetSiteList.MesonetSite) {
        mStid = inStid
        mLat = inSiteModel.GetLat()
        mElev = inSiteModel.GetElev()
        mStnm = inSiteModel.GetStnm()
        mName = inSiteModel.GetName()
        mLon = inSiteModel.GetLon()
    }


    fun ToSiteModel(): MesonetSiteListModel.MesonetSiteModel {
        val site = MesonetSiteListModel.MesonetSiteModel()

        site.SetLat(mLat)
        site.SetElev(mElev)
        site.SetStnm(mStnm)
        site.SetName(mName)
        site.SetLon(mLon)

        return site
    }


    fun GetStid(): String? {
        return mStid
    }
}