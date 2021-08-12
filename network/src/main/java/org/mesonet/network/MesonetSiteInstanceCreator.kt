package org.mesonet.network

import com.google.gson.InstanceCreator
import org.mesonet.models.site.MesonetSiteList
import org.mesonet.models.site.MesonetSiteListModel
import java.lang.reflect.Type

class MesonetSiteInstanceCreator: InstanceCreator<MesonetSiteList.MesonetSite>
{
    override fun createInstance(type: Type?): MesonetSiteList.MesonetSite {
        return MesonetSiteListModel.MesonetSiteModel()
    }

}