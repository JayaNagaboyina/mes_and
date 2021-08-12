package org.mesonet.models.site



interface MesonetSiteList: MutableMap<String, MesonetSiteList.MesonetSite> {
    interface MesonetSite
    {
        fun GetLat(): String?
        fun GetElev(): String?
        fun GetStnm(): String?
        fun GetName(): String?
        fun GetLon(): String?
        fun GetDatd(): String?
    }
}