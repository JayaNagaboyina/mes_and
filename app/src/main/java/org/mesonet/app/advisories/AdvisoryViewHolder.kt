package org.mesonet.app.advisories

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import org.mesonet.app.R

import org.mesonet.app.baseclasses.RecyclerViewHolder
import org.mesonet.app.databinding.AdvisoryViewHolderBinding
import org.mesonet.app.webview.WebViewActivity
import org.mesonet.dataprocessing.advisories.AdvisoryDisplayListBuilder

class AdvisoryViewHolder(inBinding: AdvisoryViewHolderBinding) : RecyclerViewHolder<Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>, AdvisoryViewHolderBinding>(inBinding) {


    override fun SetData(inData: Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>?) {
        val binding = GetBinding()

        if(binding != null) {
            val advisoryData = inData?.second
            binding.countyListTextView.text = advisoryData?.Counties()

            binding.root.setOnClickListener {
                it.isEnabled = false
                val intent = Intent(binding.root.context, WebViewActivity::class.java)
                intent.putStringArrayListExtra(WebViewActivity.kTitles, arrayListOf(advisoryData?.AdvisoryType()))
                intent.putStringArrayListExtra(WebViewActivity.kUrls, arrayListOf(advisoryData?.Url()))
                intent.putExtra(WebViewActivity.kUseGoogleDocs, true)
                binding.root.context.startActivity(intent)
                it.isEnabled = true
            }
        }
    }



    companion object {
        fun NewInstance(inParent: ViewGroup): AdvisoryViewHolder {
            return AdvisoryViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(inParent.context), R.layout.advisory_view_holder, inParent, false) as AdvisoryViewHolderBinding)
        }
    }
}
