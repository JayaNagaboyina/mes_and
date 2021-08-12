package org.mesonet.app.maps

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.squareup.picasso.Picasso
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.mesonet.app.R

import org.mesonet.app.baseclasses.RecyclerViewHolder
import org.mesonet.app.databinding.MapsProductViewHolderBinding
import org.mesonet.app.webview.WebViewActivity
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.models.maps.MapsList


class MapsProductViewHolder(inBinding: MapsProductViewHolderBinding, private val mMapsDataProvider: MapsDataProvider) : RecyclerViewHolder<Triple<Map.Entry<String, MapsList.Product>, MapsList.GroupSection?, MapsList.Group>, MapsProductViewHolderBinding>(inBinding) {
    private var mDisposable: Disposable? = null

    override fun SetData(inData: Triple<Map.Entry<String, MapsList.Product>, MapsList.GroupSection?, MapsList.Group>?)
    {
        if(inData is Triple<Map.Entry<String, MapsList.Product>, MapsList.GroupSection?, MapsList.Group>) {
            val binding = GetBinding()

            binding?.productTitle?.text = inData.first.value.GetTitle()
            binding?.productSection?.text = inData.second?.GetTitle()

            if (inData.second == null || inData.second?.GetTitle()?.isEmpty() != false)
                binding?.productSection?.visibility = View.GONE
            else
                binding?.productSection?.visibility = View.VISIBLE


            Picasso.get().load(inData.first.value.GetThumbnail()).into(binding?.productPreview)

            binding?.layout?.setOnClickListener {
                mMapsDataProvider.GetSections(inData.third.GetSections()).map {sections -> sections.flatMap { section -> section.value.GetProducts() } }.map {productIds -> mMapsDataProvider.GetProducts(productIds) }.flatMap { observable -> observable }.observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<LinkedHashMap<String, MapsList.Product>>{
                    override fun onComplete() {}

                    override fun onSubscribe(d: Disposable) {
                        mDisposable?.dispose()
                        mDisposable = d
                    }

                    override fun onNext(products: LinkedHashMap<String, MapsList.Product>) {
                        val titlesList: ArrayList<String> = arrayListOf()
                        val urlsList: ArrayList<String> = arrayListOf()

                        products.forEach{ product ->
                            titlesList.add(product.value.GetTitle()?: "")
                            urlsList.add(product.value.GetUrl()?: "")
                        }

                        it.isEnabled = false
                        val intent = Intent(binding.root.context, WebViewActivity::class.java)
                        intent.putStringArrayListExtra(WebViewActivity.kTitles, titlesList)
                        intent.putStringArrayListExtra(WebViewActivity.kUrls, urlsList)
                        intent.putExtra(WebViewActivity.kStartIndex, products.keys.indexOf(inData.first.key))
                        intent.putExtra(WebViewActivity.kAllowShare, true)
                        intent.putExtra(WebViewActivity.kInitialZoom, 1)
                        intent.putExtra(WebViewActivity.kAllowUserZoom, true)
                        intent.putExtra(WebViewActivity.kUpdateInterval, 150000L)
                        binding.root.context.startActivity(intent)

                        it.isEnabled = true
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
            }
        }
    }


    companion object {
        fun NewInstance(inParent: ViewGroup, inMapsDataProvider: MapsDataProvider): MapsProductViewHolder {
            return MapsProductViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(inParent.context), R.layout.maps_product_view_holder, inParent, false) as MapsProductViewHolderBinding, inMapsDataProvider)
        }
    }
}
