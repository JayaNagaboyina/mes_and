package org.mesonet.app.advisories

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import org.mesonet.app.R

import org.mesonet.app.baseclasses.RecyclerViewHolder
import org.mesonet.app.databinding.HeaderViewHolderBinding
import org.mesonet.dataprocessing.advisories.AdvisoryDisplayListBuilder


class AdvisoryHeaderViewHolder(inBinding: HeaderViewHolderBinding) : RecyclerViewHolder<Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>, HeaderViewHolderBinding>(inBinding) {

    override fun SetData(inData: Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>?) {

        val binding = GetBinding()

        if(binding != null)
            binding.headerText.text = (inData?.second)?.AdvisoryType()
    }

    companion object {
        fun NewInstance(inParent: ViewGroup): AdvisoryHeaderViewHolder {
            return AdvisoryHeaderViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(inParent.context), R.layout.header_view_holder, inParent, false) as HeaderViewHolderBinding)
        }
    }
}
