package org.mesonet.app.maps


import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.mesonet.app.R

import org.mesonet.app.baseclasses.BaseActivity
import org.mesonet.app.baseclasses.RecyclerViewHolder
import org.mesonet.app.databinding.MapsGroupViewHolderBinding
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.models.maps.MapsList

class MapsGroupViewHolder(private val mBaseActivity: BaseActivity?, private val mMapDataProvider: MapsDataProvider, inBinding: MapsGroupViewHolderBinding) : RecyclerViewHolder<MapsList.Group, MapsGroupViewHolderBinding>(inBinding) {
    var disposable: Disposable? = null
    var productsDisposable: Disposable? = null

    init {
        inBinding.productRecyclerView.setAdapter(MapsProductRecyclerViewAdapter(mMapDataProvider))
    }

    override fun SetData(inData: MapsList.Group?) {
        disposable?.dispose()
        productsDisposable?.dispose()

        if(inData == null)
            GetBinding()?.root?.visibility = View.GONE

        inData?.let { group ->
            mMapDataProvider.GetSections(group.GetSections()).observeOn(Schedulers.computation()).subscribe(object: Observer<LinkedHashMap<String, MapsList.GroupSection>> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    disposable?.dispose()
                    disposable = d
                }

                override fun onNext(sections: LinkedHashMap<String, MapsList.GroupSection>) {
                    val binding = GetBinding()

                    mMapDataProvider.GetProducts(sections.values.flatMap{ it.GetProducts() }).observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<LinkedHashMap<String, MapsList.Product>> {
                        override fun onComplete() {}

                        override fun onSubscribe(d: Disposable) {
                            productsDisposable?.dispose()
                            productsDisposable = d
                        }

                        override fun onNext(products: LinkedHashMap<String, MapsList.Product>) {
                            binding?.header?.headerText?.text = group.GetTitle()
                            val abbreviatedMap = linkedMapOf<String, MapsList.Product>()

                            for(i in 0 until (if(products.size > 4 ) 4 else products.size)) {
                                abbreviatedMap[products.keys.toTypedArray()[i]] = products.values.elementAt(i)
                            }
                            binding?.productRecyclerView?.GetAdapter()?.SetItems(ArrayList((abbreviatedMap).map {product -> Triple(product, sections.values.first { it.GetProducts().contains(product.key) }, inData) }))

                            if (products.size > 4)
                                binding?.viewAllLayout?.visibility = View.VISIBLE
                            else
                                binding?.viewAllLayout?.visibility = View.GONE
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }
                    })

                    binding?.viewAllLayout?.setOnClickListener {
                        it.isEnabled = false
                        val args = Bundle()

                        args.putSerializable(MapListFragment.kMapGroupFullList, inData)

                        val fragment = MapListFragment()
                        fragment.arguments = args

                        mBaseActivity?.NavigateToPage(fragment, true, R.anim.slide_from_right_animation, R.anim.slide_to_left_animation)
                        it.isEnabled = true
                    }

                    binding?.root?.visibility = View.VISIBLE
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        }
    }

    companion object {
        fun NewInstance(inActivity: BaseActivity?, inMapsDataProvider: MapsDataProvider, inParent: ViewGroup): MapsGroupViewHolder {
            return MapsGroupViewHolder(inActivity, inMapsDataProvider, DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(inParent.context), R.layout.maps_group_view_holder, inParent, false) as MapsGroupViewHolderBinding)
        }
    }
}
