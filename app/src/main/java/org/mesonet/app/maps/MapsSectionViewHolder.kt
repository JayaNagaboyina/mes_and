package org.mesonet.app.maps

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.mesonet.app.R

import org.mesonet.app.baseclasses.RecyclerViewHolder
import org.mesonet.app.databinding.MapsSectionViewHolderBinding
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.models.maps.MapsList


class MapsSectionViewHolder(inBinding: MapsSectionViewHolderBinding, private val mMapsDataProvider: MapsDataProvider,
                            val mIsFirst: Boolean) : RecyclerViewHolder<Pair<MapsList.GroupSection, MapsList.Group>, MapsSectionViewHolderBinding>(inBinding) {

    private var mDisposable: Disposable? = null

    override fun SetData(inData: Pair<MapsList.GroupSection, MapsList.Group>?) {
        mDisposable?.dispose()

        if(inData == null)
            GetBinding()?.root?.visibility = View.GONE

        inData?.first?.let {section ->
            mMapsDataProvider.GetProducts(section.GetProducts()).observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<LinkedHashMap<String, MapsList.Product>>{
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    mDisposable?.dispose()
                    mDisposable = d
                }

                override fun onNext(t: LinkedHashMap<String, MapsList.Product>) {
                    val binding = GetBinding()

                    binding?.productRecyclerView?.setAdapter(MapsProductRecyclerViewAdapter(mMapsDataProvider))

                    binding?.header?.headerText?.text = section.GetTitle()

                    if (mIsFirst && binding?.header?.headerText?.text.isNullOrBlank())
                        binding?.header?.headerText?.visibility = View.GONE

                    binding?.productRecyclerView?.SetItems(ArrayList(t.map { Triple(it, null, inData.second) }))
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        }
    }

    companion object {
        fun NewInstance(inParent: ViewGroup, inMapsDataProvider: MapsDataProvider, inIsFirst: Boolean): MapsSectionViewHolder {
            return MapsSectionViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(inParent.context), R.layout.maps_section_view_holder, inParent, false) as MapsSectionViewHolderBinding, inMapsDataProvider, inIsFirst)
        }
    }
}
