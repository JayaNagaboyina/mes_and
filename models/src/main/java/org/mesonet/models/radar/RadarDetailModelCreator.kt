package org.mesonet.models.radar

import com.google.gson.Gson
import javax.inject.Inject



class RadarDetailModelCreator @Inject constructor() : RadarDetailCreator
{
    override fun ParseRadarJson(inJson: String): Map<String, RadarDetails> {
        return Gson().fromJson(inJson, RadarModel.Map::class.java)
    }
}