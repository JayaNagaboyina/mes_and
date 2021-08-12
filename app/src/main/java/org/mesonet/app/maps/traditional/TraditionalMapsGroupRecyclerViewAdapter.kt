package org.mesonet.app.maps.traditional

import android.view.ViewGroup
import org.mesonet.app.baseclasses.BaseActivity
import org.mesonet.app.baseclasses.RecyclerViewAdapter
import org.mesonet.app.baseclasses.RecyclerViewHolder
import org.mesonet.app.databinding.TraditionalMapsViewHolderBinding
import org.mesonet.models.maps.MapsList


class TraditionalMapsGroupRecyclerViewAdapter(private val mBaseActivity: BaseActivity) : RecyclerViewAdapter<MapsList.Group, RecyclerViewHolder<MapsList.Group, TraditionalMapsViewHolderBinding>>() {
    override fun onCreateViewHolder(inParent: ViewGroup, inViewType: Int): RecyclerViewHolder<MapsList.Group, TraditionalMapsViewHolderBinding> {
        return TraditionalMapsGroupViewHolder.NewInstance(mBaseActivity, inParent)
    }
}