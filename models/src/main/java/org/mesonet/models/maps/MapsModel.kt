package org.mesonet.models.maps



import com.google.gson.annotations.SerializedName

class MapsModel: MapsList
{
    @SerializedName("main")
    private val mMain: List<MainModel>? = null

    @SerializedName("sections")
    private lateinit var mSections: LinkedHashMap<String, SectionModel>

    @SerializedName("products")
    private lateinit var mProducts: LinkedHashMap<String, ProductModel>


    override fun GetMain(): List<MapsList.Group>? {
        return mMain
    }


    override fun GetSections(): LinkedHashMap<String, MapsList.GroupSection> {
        return mSections as LinkedHashMap<String, MapsList.GroupSection>
    }

    override fun GetProducts(): LinkedHashMap<String, MapsList.Product> {
        return mProducts as LinkedHashMap<String, MapsList.Product>
    }



    class MainModel: MapsList.Group {
        @SerializedName("sections")
        private lateinit var mSections: List<String>

        @SerializedName("title")
        private lateinit var mTitle: String


        override fun GetSections(): List<String> {
            return mSections
        }


        override fun GetTitle(): String? {
            return mTitle
        }
    }


    class SectionModel: MapsList.GroupSection {
        @SerializedName("title")
        private var mTitle: String? = null

        @SerializedName("products")
        private lateinit var mProducts: List<String>


        override fun GetTitle(): String?
        {
            return mTitle
        }


        override fun GetProducts(): List<String>
        {
            return mProducts
        }
    }


    class ProductModel: MapsList.Product {
        @SerializedName("url")
        private var mUrl: String? = null

        @SerializedName("title")
        private var mTitle: String? = null

        @SerializedName("thumbnail")
        private var mThumbnail: String? = null


        override fun GetUrl(): String?
        {
            return mUrl
        }


        override fun GetTitle(): String?
        {
            return mTitle?.replace("&deg;", "°")
        }


        override fun GetThumbnail(): String?
        {
            return mThumbnail
        }
    }
}