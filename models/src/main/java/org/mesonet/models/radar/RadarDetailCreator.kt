package org.mesonet.models.radar

import org.xmlpull.v1.XmlPullParser
import java.io.InputStream

interface RadarDetailCreator
{
    fun ParseRadarJson(inJson: String): Map<String, RadarDetails>
}