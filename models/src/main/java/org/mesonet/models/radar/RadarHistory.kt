package org.mesonet.models.radar

interface RadarHistory: Comparable<RadarHistory>
{
    fun GetTimeStampUTC(): String
    fun GetRadar(): String
    fun GetFrames(): List<RadarFrame>

    interface RadarFrame: Comparable<RadarFrame>
    {
        fun GetTimestring(): String
        fun GetTime(): Long
        fun GetFrameId(): String
    }
}