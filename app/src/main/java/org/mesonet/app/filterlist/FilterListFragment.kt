package org.mesonet.app.filterlist


import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.futuremind.recyclerviewfastscroll.FastScroller
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

import org.mesonet.app.BasicViewHolder
import org.mesonet.app.MainActivity
import org.mesonet.app.R
import org.mesonet.app.baseclasses.BaseFragment
import org.mesonet.app.baseclasses.RecyclerViewAdapter
import org.mesonet.app.databinding.FilterListFragmentBinding
import org.mesonet.dataprocessing.BasicListData
import org.mesonet.dataprocessing.SelectSiteListener
import org.mesonet.dataprocessing.filterlist.FilterListController
import org.mesonet.dataprocessing.filterlist.FilterListDataProvider

import javax.inject.Inject


class FilterListFragment : BaseFragment(), SelectSiteListener, Observer<MutableList<Pair<String, BasicListData>>> {
    private var mTextChangedListener: TextWatcher? = null


    var mDisposable: Disposable? = null

    private lateinit var mBinding: FilterListFragmentBinding

    @Inject
    internal lateinit var mFilterListCloser: FilterListCloser

    @Inject
    internal lateinit var mMainActivity: MainActivity

    @Inject
    internal lateinit var mSelectedListener: SelectSiteListener

    @Inject
    internal lateinit var mFilterListData: FilterListDataProvider

    @Inject
    internal lateinit var mFilterListController: FilterListController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.filter_list_fragment, container, false)

        mBinding.searchList.setAdapter(FilterListAdapter())

        //has to be called AFTER RecyclerView.setAdapter()
        mBinding.fastscroll.setRecyclerView(mBinding.searchList)

        val closeDrawable = resources.getDrawable(R.drawable.ic_close_white_36dp, context?.theme)

        mBinding.siteSelectionToolbar.navigationIcon = closeDrawable
        mBinding.siteSelectionToolbar.setNavigationOnClickListener { Close() }

        mBinding.siteSelectionToolbar.inflateMenu(R.menu.search_list_menu)

        mBinding.siteSelectionToolbar.menu.findItem(R.id.nearestLocation).setOnMenuItemClickListener {
            mDisposable?.dispose()
            mDisposable = null
            mFilterListData.AsBasicListData().flatMap {t ->
                mFilterListController.SortByNearest(mBinding.searchText.text.toString(), t.first).toObservable()
            }.observeOn(AndroidSchedulers.mainThread()).subscribe(this@FilterListFragment)

            false
        }

        closeDrawable.setTint(resources.getColor(R.color.lightTextColor, activity?.theme))
        mBinding.searchText.setTextColor(resources.getColor(R.color.lightTextColor, activity?.theme))

        mTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                mDisposable?.dispose()
                mDisposable = null
                mFilterListData.AsBasicListData().flatMap {
                    mFilterListController.SortBySortString(mBinding.searchText.text.toString(), it.first).toObservable()
                }.observeOn(AndroidSchedulers.mainThread()).subscribe(this@FilterListFragment)
            }

            override fun afterTextChanged(editable: Editable) {
            }
        }

        mDisposable?.dispose()
        mDisposable = null
        mFilterListData.AsBasicListData().observeOn(AndroidSchedulers.mainThread()).flatMap {
            if (it.first.containsKey(mFilterListData.CurrentSelection()))
                mBinding.searchText.setText(it.first[mFilterListData.CurrentSelection()]?.GetName())
            mBinding.searchText.addTextChangedListener(mTextChangedListener)

            mFilterListController.TryGetLocationAndFillListObservable(mBinding.searchText.text.toString(), it.first).toObservable()

        }.observeOn(AndroidSchedulers.mainThread()).subscribe(this@FilterListFragment)

        return mBinding.root
    }



    override fun onDestroyView() {
        mBinding.searchText.removeTextChangedListener(mTextChangedListener)
        mTextChangedListener = null
        mDisposable?.dispose()
        mDisposable = null
        super.onDestroyView()
    }


    override fun SetResult(inResult: String) {
        Observable.create(ObservableOnSubscribe<Void> {
            Close()
            mSelectedListener.SetResult(inResult)
            it.onComplete()
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<Void> {
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: Void) {}
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }


    internal fun Close()
    {
        mMainActivity.CloseKeyboard()
        mFilterListCloser.Close()
    }


    override fun onSubscribe(d: Disposable)
    {
        mDisposable = d
    }
    override fun onError(e: Throwable)
    {
        e.printStackTrace()
    }

    override fun onNext(t: MutableList<Pair<String, BasicListData>>) {
        mBinding.searchList.SetItems(t)
    }

    override fun onComplete() {}


    interface FilterListCloser {
        fun Close()
    }


    private inner class FilterListAdapter : RecyclerViewAdapter<Pair<String, BasicListData>, BasicViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder {
            return BasicViewHolder(parent, this@FilterListFragment)
        }
    }
}
