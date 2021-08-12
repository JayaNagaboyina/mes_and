package org.mesonet.app.maps

import android.view.ViewGroup
import org.mesonet.app.baseclasses.RecyclerViewAdapter
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.models.maps.MapsList

class MapsSectionRecyclerViewAdapter(private val mMapsDataProvider: MapsDataProvider) : RecyclerViewAdapter<Pair<MapsList.GroupSection, MapsList.Group>, MapsSectionViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsSectionViewHolder {
        return MapsSectionViewHolder.NewInstance(parent, mMapsDataProvider, viewType == 0)
    }
}