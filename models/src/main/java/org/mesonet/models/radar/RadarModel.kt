package org.mesonet.models.radar

import com.google.gson.annotations.SerializedName


class RadarModel : RadarDetails {

    @SerializedName("name")
    private val mName: String? = null

    @SerializedName("latitude")
    private val mLatitude: Float? = null

    @SerializedName("longitude")
    private val mLongitude: Float? = null

    @SerializedName("southWest")
    private val mSouthWest: CornerModel? = null

    @SerializedName("northEast")
    private val mNorthEast: CornerModel? = null


    override fun GetLatitude(): Float? {
        return mLatitude
    }


    override fun GetLongitude(): Float? {
        return mLongitude
    }


    override fun GetName(): String? {
        return mName
    }


    override fun GetSouthWestCorner(): RadarDetails.Corner? {
        return mSouthWest
    }


    override fun GetNorthEastCorner(): RadarDetails.Corner? {
        return mNorthEast
    }


    class CornerModel: RadarDetails.Corner
    {
        @SerializedName("latitude")
        private val mLatitude: Float? = null

        @SerializedName("longitude")
        private val mLongitude: Float? = null

        override fun GetLatitude(): Float? {
            return mLatitude
        }


        override fun GetLongitude(): Float? {
            return mLongitude
        }
    }

    class Map: HashMap<String, RadarModel>()
}
