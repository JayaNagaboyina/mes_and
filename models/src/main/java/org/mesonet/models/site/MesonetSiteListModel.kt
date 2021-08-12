package org.mesonet.models.site

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

import java.util.HashMap

@Keep
class MesonetSiteListModel : HashMap<String, MesonetSiteList.MesonetSite>(), MesonetSiteList {
    class MesonetSiteModel: MesonetSiteList.MesonetSite {

        @SerializedName("lat")
        internal var mLat: String? = null

        @SerializedName("elev")
        internal var mElev: String? = null

        @SerializedName("datd")
        internal var mDatd: String? = null

        @SerializedName("stnm")
        internal var mStnm: String? = null

        @SerializedName("name")
        internal var mName: String? = null

        @SerializedName("lon")
        internal var mLon: String? = null

        override fun GetLat(): String? {
            return mLat
        }

        override fun GetElev(): String? {
            return mElev
        }

        override fun GetStnm(): String? {
            return mStnm
        }

        override fun GetName(): String? {
            return mName
        }

        override fun GetLon(): String? {
            return mLon
        }


        override fun GetDatd(): String? {
            return mDatd
        }


        fun SetLat(inLat: String?) {
            mLat = inLat
        }

        fun SetElev(inElev: String?) {
            mElev = inElev
        }

        fun SetStnm(inStnm: String?) {
            mStnm = inStnm
        }

        fun SetName(inName: String?) {
            mName = inName
        }

        fun SetLon(inLon: String?) {
            mLon = inLon
        }
    }
}