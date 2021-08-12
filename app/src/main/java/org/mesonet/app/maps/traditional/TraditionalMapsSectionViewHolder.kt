package org.mesonet.app.maps.traditional

import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.mesonet.app.R
import org.mesonet.app.baseclasses.RecyclerViewHolder
import org.mesonet.app.databinding.TraditionalMapsSectionViewHolderBinding
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.models.maps.MapsList

class TraditionalMapsSectionViewHolder(inBinding: TraditionalMapsSectionViewHolderBinding, private val mMapsDataProvider: MapsDataProvider) : RecyclerViewHolder<Pair<MapsList.GroupSection, MapsList.Group>, TraditionalMapsSectionViewHolderBinding>(inBinding) {
    private var mDisposable: Disposable? = null

    companion object {
        fun NewInstance(inParent: ViewGroup, inMapsDataProvider: MapsDataProvider): TraditionalMapsSectionViewHolder {
            return TraditionalMapsSectionViewHolder(DataBindingUtil.inflate(LayoutInflater.from(inParent.context), R.layout.traditional_maps_section_view_holder, inParent, false), inMapsDataProvider)
        }
    }

    init {
        GetBinding()?.productRecyclerView?.setAdapter(TraditionalMapsProductRecyclerViewAdapter(mMapsDataProvider))
    }

    override fun SetData(inData: Pair<MapsList.GroupSection, MapsList.Group>?) {
        mDisposable?.dispose()

        inData?.let {data ->
            mMapsDataProvider.GetProducts(data.first.GetProducts()).observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<LinkedHashMap<String, MapsList.Product>>{
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    mDisposable?.dispose()
                    mDisposable = d
                }

                override fun onNext(inProducts: LinkedHashMap<String, MapsList.Product>) {
                    val list = mutableListOf<Pair<Map.Entry<String, MapsList.Product>, MapsList.Group>>()

                    inProducts.forEach{
                        list.add(Pair(it, inData.second))
                    }

                    GetBinding()?.productRecyclerView?.SetItems(list)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        }
    }
}