package org.mesonet.app.maps

import android.view.ViewGroup
import org.mesonet.app.baseclasses.RecyclerViewAdapter
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.models.maps.MapsList


class MapsProductRecyclerViewAdapter(private val mMapsDataProvider: MapsDataProvider): RecyclerViewAdapter<Triple<Map.Entry<String, MapsList.Product>, MapsList.GroupSection?, MapsList.Group>, MapsProductViewHolder>()
{
    override fun onCreateViewHolder(inParent: ViewGroup, inViewType: Int): MapsProductViewHolder
    {
        return MapsProductViewHolder.NewInstance(inParent, mMapsDataProvider)
    }
}