package org.mesonet.app


import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import org.mesonet.app.baseclasses.RecyclerViewHolder

import org.mesonet.app.databinding.BasicViewHolderBinding
import org.mesonet.dataprocessing.BasicListData
import org.mesonet.dataprocessing.SelectSiteListener

class BasicViewHolder(inParent: ViewGroup, private var mSelectedListener: SelectSiteListener) : RecyclerViewHolder<Pair<String, BasicListData>, BasicViewHolderBinding>(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(inParent.context), R.layout.basic_view_holder, inParent, false)) {

    val mContext = inParent.context

    override fun SetData(inData: Pair<String, BasicListData>?) {
        GetBinding()?.favorite = inData?.second?.IsFavorite()?: false
        GetBinding()?.text = inData?.second?.GetName()
        GetBinding()?.root?.setOnClickListener {
            it.isEnabled = false
            mSelectedListener.SetResult(inData?.first?: "")
            it.isEnabled = true
        }
    }
}