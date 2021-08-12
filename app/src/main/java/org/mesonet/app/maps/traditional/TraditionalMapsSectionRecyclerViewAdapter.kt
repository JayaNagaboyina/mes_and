package org.mesonet.app.maps.traditional

import android.view.ViewGroup
import org.mesonet.app.baseclasses.RecyclerViewAdapter
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.models.maps.MapsList

class TraditionalMapsSectionRecyclerViewAdapter(private val mMapsDataProvider: MapsDataProvider) : RecyclerViewAdapter<Pair<MapsList.GroupSection, MapsList.Group>, TraditionalMapsSectionViewHolder>() {
    override fun onCreateViewHolder(inParent: ViewGroup, inViewType: Int): TraditionalMapsSectionViewHolder {
        return TraditionalMapsSectionViewHolder.NewInstance(inParent, mMapsDataProvider)
    }
}