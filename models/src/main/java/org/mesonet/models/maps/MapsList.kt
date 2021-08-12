package org.mesonet.models.maps

import java.io.Serializable


interface MapsList
{
    fun GetMain(): List<Group>?
    fun GetSections(): LinkedHashMap<String, GroupSection>
    fun GetProducts(): LinkedHashMap<String, Product>


    interface Group: Serializable
    {
        fun GetSections(): List<String>
        fun GetTitle(): String?
    }


    interface GroupSection
    {
        fun GetTitle(): String?
        fun GetProducts(): List<String>
    }


    interface Product
    {
        fun GetUrl(): String?
        fun GetTitle(): String?
        fun GetThumbnail(): String?
    }
}