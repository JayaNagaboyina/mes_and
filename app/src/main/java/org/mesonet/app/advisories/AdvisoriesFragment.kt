package org.mesonet.app.advisories

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.mesonet.app.R

import org.mesonet.app.baseclasses.BaseFragment
import org.mesonet.app.databinding.AdvisoriesFragmentBinding
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.dataprocessing.advisories.AdvisoryDataProvider
import org.mesonet.dataprocessing.advisories.AdvisoryDisplayListBuilder


import javax.inject.Inject


class AdvisoriesFragment : BaseFragment()
{
    private var mBinding: AdvisoriesFragmentBinding? = null

    @Inject
    internal lateinit var mAdvisoryDataProvider: AdvisoryDataProvider

    @Inject
    internal lateinit var mAdvisoryListBuilder: AdvisoryDisplayListBuilder

    var mAdvisoryDisposable: Disposable? = null
    var mPageStateDisposable: Disposable? = null



    override fun onCreateView(inInflater: LayoutInflater, inParent: ViewGroup?, inSavedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inInflater, R.layout.advisories_fragment, inParent, false)

        mBinding?.advisoriesRecyclerView?.setAdapter(AdvisoriesRecyclerViewAdapter())

        return mBinding?.root
    }


    override fun onResume() {
        super.onResume()

        if(mPageStateDisposable?.isDisposed != false) {
            mAdvisoryDataProvider.GetPageStateObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<PageStateInfo> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    mPageStateDisposable = d
                }

                override fun onNext(t: PageStateInfo) {
                    when(t.GetPageState())
                    {
                        PageStateInfo.PageState.kData ->
                        {
                            (mBinding?.advisoriesRecyclerView as RecyclerView?)?.visibility = View.VISIBLE
                            mBinding?.errorText?.visibility = View.GONE
                            mBinding?.progressBar?.visibility = View.GONE
                        }
                        PageStateInfo.PageState.kLoading ->
                        {
                            (mBinding?.advisoriesRecyclerView as RecyclerView?)?.visibility = View.GONE
                            mBinding?.errorText?.visibility = View.GONE
                            mBinding?.progressBar?.visibility = View.VISIBLE
                        }
                        PageStateInfo.PageState.kError ->
                        {
                            (mBinding?.advisoriesRecyclerView as RecyclerView?)?.visibility = View.VISIBLE
                            mBinding?.errorText?.text = t.GetErrorMessage()
                            mBinding?.errorText?.visibility = View.VISIBLE
                            mBinding?.progressBar?.visibility = View.GONE
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
        }

        if(mAdvisoryDisposable?.isDisposed != false) {
            mAdvisoryDataProvider.GetDataObservable().observeOn(AndroidSchedulers.mainThread()).flatMap { list ->
                mAdvisoryListBuilder.BuildList(list)
            }.observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<ArrayList<Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>>> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {
                    mAdvisoryDisposable = d
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    if (mBinding != null) {
                        mBinding?.progressBar?.visibility = View.GONE
                        mBinding?.errorText?.visibility = View.VISIBLE
                    }
                }

                override fun onNext(t: ArrayList<Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>>) {
                    if (mBinding != null) {
                        mBinding?.progressBar?.visibility = View.GONE
                        mBinding?.advisoriesRecyclerView?.SetItems(t)

                        if (t.size == 0)
                            mBinding?.errorText?.visibility = View.VISIBLE
                        else
                            mBinding?.errorText?.visibility = View.GONE
                    }
                }
            })
        }
    }


    override fun onPause() {
        mAdvisoryDisposable?.dispose()
        mAdvisoryDisposable = null
        mPageStateDisposable?.dispose()
        mPageStateDisposable = null
        super.onPause()
    }
}
