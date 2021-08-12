package org.mesonet.app.baseclasses

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.AttributeSet
import androidx.core.view.ViewCompat


class RecyclerView<TRecyclerViewAdapter : RecyclerViewAdapter<Any, RecyclerViewHolder<Any, ViewDataBinding>>> @JvmOverloads constructor(inContext: Context, inAttrs: AttributeSet? = null, inDefStyle: Int = 0) : androidx.recyclerview.widget.RecyclerView(inContext, inAttrs, inDefStyle) {



    init {

        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(
            inContext,
            VERTICAL, false

        )
    }


    internal fun GetAdapter(): TRecyclerViewAdapter? {
        try {
            return adapter as TRecyclerViewAdapter
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

        return null
    }


    @Synchronized
    internal fun SetItems(inData: MutableList<Any>) {
        recycledViewPool.clear()
        GetAdapter()?.SetItems(inData)
    }


    fun finalize() {
        adapter = null
    }
}
