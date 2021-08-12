package org.mesonet.app.maps.traditional

import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.mesonet.app.R
import org.mesonet.app.baseclasses.RecyclerViewHolder
import org.mesonet.app.databinding.TraditionalMapsViewHolderBinding
import org.mesonet.app.webview.WebViewActivity
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.models.maps.MapsList

class TraditionalMapsProductViewHolder(inBinding: TraditionalMapsViewHolderBinding, private var mMapsDataProvider: MapsDataProvider) : RecyclerViewHolder<Pair<Map.Entry<String, MapsList.Product>, MapsList.Group>, TraditionalMapsViewHolderBinding>(inBinding) {
    private var mDisposable: Disposable? = null

    companion object {
        fun NewInstance(inParent: ViewGroup, mMapsDataProvider: MapsDataProvider): TraditionalMapsProductViewHolder {
            return TraditionalMapsProductViewHolder(DataBindingUtil.inflate(LayoutInflater.from(inParent.context), R.layout.traditional_maps_view_holder, inParent, false), mMapsDataProvider)
        }
    }
    override fun SetData(inData: Pair<Map.Entry<String, MapsList.Product>, MapsList.Group>?) {
        val binding = GetBinding()

        binding?.title = inData?.first?.value?.GetTitle()

        binding?.layout?.setOnClickListener {
            mMapsDataProvider.GetSections(inData?.second?.GetSections()
                    ?: arrayListOf()).map { sections -> sections.flatMap { section -> section.value.GetProducts() } }.map { productIds -> mMapsDataProvider.GetProducts(productIds) }.flatMap { observable -> observable }.observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<LinkedHashMap<String, MapsList.Product>> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    mDisposable?.dispose()
                    mDisposable = d
                }

                override fun onNext(products: LinkedHashMap<String, MapsList.Product>) {
                    val titlesList: ArrayList<String> = arrayListOf()
                    val urlsList: ArrayList<String> = arrayListOf()

                    products.forEach { product ->
                        titlesList.add(product.value.GetTitle() ?: "")
                        urlsList.add(product.value.GetUrl() ?: "")
                    }

                    it.isEnabled = false
                    val intent = Intent(binding.root.context, WebViewActivity::class.java)
                    intent.putStringArrayListExtra(WebViewActivity.kTitles, titlesList)
                    intent.putStringArrayListExtra(WebViewActivity.kUrls, urlsList)
                    intent.putExtra(WebViewActivity.kStartIndex, products.keys.indexOf(inData?.first?.key))
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