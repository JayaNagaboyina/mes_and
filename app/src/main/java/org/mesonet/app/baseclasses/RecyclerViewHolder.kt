package org.mesonet.app.baseclasses

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class RecyclerViewHolder<in TData, out TViewDataBinding : ViewDataBinding>(protected var mBinding: ViewDataBinding) : RecyclerView.ViewHolder(mBinding.root) {


    abstract internal fun SetData(inData: TData?)


    internal fun GetBinding(): TViewDataBinding? {
        try {
            return mBinding as TViewDataBinding
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

        return null
    }
}
