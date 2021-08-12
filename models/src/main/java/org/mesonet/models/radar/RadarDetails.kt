package org.mesonet.models.radar

interface RadarDetails
{
    fun GetLatitude(): Float?
    fun GetLongitude(): Float?
    fun GetSouthWestCorner(): Corner?
    fun GetNorthEastCorner(): Corner?
    fun GetName(): String?

    interface Corner
    {
        fun GetLatitude(): Float?
        fun GetLongitude(): Float?
    }
}
