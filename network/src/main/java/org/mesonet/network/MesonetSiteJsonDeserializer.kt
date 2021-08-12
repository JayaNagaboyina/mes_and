package org.mesonet.network

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.mesonet.models.site.MesonetSiteList
import org.mesonet.models.site.MesonetSiteListModel
import java.lang.reflect.Type

class MesonetSiteJsonDeserializer: JsonDeserializer<MesonetSiteList.MesonetSite>
{
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): MesonetSiteList.MesonetSite {
        return Gson().fromJson(json.toString(), MesonetSiteListModel.MesonetSiteModel::class.java)
    }

}