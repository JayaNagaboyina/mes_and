package org.mesonet.app.maps.traditional

import android.view.ViewGroup
import org.mesonet.app.baseclasses.RecyclerViewAdapter
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.models.maps.MapsList

class TraditionalMapsProductRecyclerViewAdapter(private val mMapsDataProvider: MapsDataProvider) : RecyclerViewAdapter<Pair<Map.Entry<String, MapsList.Product>, MapsList.Group>, TraditionalMapsProductViewHolder>() {
    override fun onCreateViewHolder(inParent: ViewGroup, inViewGroup: Int): TraditionalMapsProductViewHolder {
        return TraditionalMapsProductViewHolder.NewInstance(inParent, mMapsDataProvider)
    }
}