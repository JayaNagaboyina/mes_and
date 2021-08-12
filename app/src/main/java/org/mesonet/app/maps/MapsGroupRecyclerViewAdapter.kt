package org.mesonet.app.maps

import androidx.databinding.ViewDataBinding
import android.view.ViewGroup

import org.mesonet.app.baseclasses.BaseActivity
import org.mesonet.app.baseclasses.RecyclerViewAdapter
import org.mesonet.app.baseclasses.RecyclerViewHolder
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.models.maps.MapsList


class MapsGroupRecyclerViewAdapter(private val mBaseActivity: BaseActivity?, private val mMapsDataProvider: MapsDataProvider) : RecyclerViewAdapter<MapsList.Group, RecyclerViewHolder<MapsList.Group, ViewDataBinding>>() {
    override fun onCreateViewHolder(inParent: ViewGroup, inViewType: Int): RecyclerViewHolder<MapsList.Group, ViewDataBinding> {
        return MapsGroupViewHolder.NewInstance(mBaseActivity, mMapsDataProvider, inParent)
    }
}
