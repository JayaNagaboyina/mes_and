package org.mesonet.app.maps

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.app.R

import org.mesonet.app.baseclasses.BaseActivity
import org.mesonet.app.baseclasses.BaseFragment
import org.mesonet.app.databinding.MapListFragmentBinding
import org.mesonet.app.maps.traditional.TraditionalMapsGroupRecyclerViewAdapter
import org.mesonet.app.maps.traditional.TraditionalMapsSectionRecyclerViewAdapter
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.dataprocessing.userdata.Preferences
import org.mesonet.models.maps.MapsList

import javax.inject.Inject


class MapListFragment : BaseFragment()
{
    companion object {
        const val kMapGroupFullList = "mapGroupFullList"
    }

    private lateinit var mBinding: MapListFragmentBinding

    private var mGroupListDataDisposable: Disposable? = null
    private var mPageStateDisposable: Disposable? = null
    private var mDataAndDisplayModeDisposable: Disposable? = null

    @Inject
    internal lateinit var mDataProvider: MapsDataProvider

    @Inject
    internal lateinit var mActivity: BaseActivity

    @Inject
    internal lateinit var mPreferences: Preferences

    private val mGroupsListSubject: BehaviorSubject<MapsList> = BehaviorSubject.create()
    private val mSectionListSubject: BehaviorSubject<LinkedHashMap<String, MapsList.GroupSection>> = BehaviorSubject.create()

    private var mGroup: MapsList.Group? = null


    override fun onCreateView(inInflater: LayoutInflater, inParent: ViewGroup?, inSavedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inInflater, R.layout.map_list_fragment, inParent, false)

        if (arguments != null && arguments?.containsKey(kMapGroupFullList) == true)
            mGroup = arguments?.getSerializable(kMapGroupFullList) as MapsList.Group

        if(mGroup == null) {

            mDataProvider.GetMapsListObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<MapsList?>
            {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable)
                {
                    mGroupListDataDisposable?.dispose()
                    mGroupListDataDisposable = d
                }

                override fun onNext(t: MapsList)
                {
                    mGroupsListSubject.onNext(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })

            if(mPageStateDisposable?.isDisposed != false) {
                mDataProvider.GetPageStateObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<PageStateInfo>{
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {
                        mPageStateDisposable = d
                    }

                    override fun onNext(t: PageStateInfo) {
                        when(t.GetPageState())
                        {
                            PageStateInfo.PageState.kData ->
                            {
                                mBinding.groupNameToolbar.visibility = View.GONE
                                (mBinding.mapList as RecyclerView).visibility = View.VISIBLE
                                mBinding.progressBar.visibility = View.GONE
                                mBinding.emptyListText.visibility = View.GONE
                            }
                            PageStateInfo.PageState.kLoading ->
                            {
                                mBinding.groupNameToolbar.visibility = View.GONE
                                (mBinding.mapList as RecyclerView).visibility = View.GONE
                                mBinding.progressBar.visibility = View.VISIBLE
                                mBinding.emptyListText.visibility = View.GONE
                            }
                            PageStateInfo.PageState.kError ->
                            {
                                mBinding.groupNameToolbar.visibility = View.GONE
                                (mBinding.mapList as RecyclerView).visibility = View.GONE
                                mBinding.progressBar.visibility = View.GONE
                                mBinding.emptyListText.visibility = View.VISIBLE
                                mBinding.emptyListText.text = t.GetErrorMessage()
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
            }
        }
        else
        {
            mGroup?.let { group ->
                mBinding.progressBar.visibility = View.GONE
                mBinding.emptyListText.visibility = View.GONE
                (mBinding.mapList as RecyclerView).visibility = View.VISIBLE
                mBinding.groupNameToolbar.title = group.GetTitle()

                mBinding.groupNameToolbar.visibility = View.VISIBLE

                mDataProvider.GetSections(group.GetSections()).observeOn(AndroidSchedulers.mainThread()).subscribe(mSectionListSubject)
            }
        }

        return mBinding.root
    }


    override fun onResume() {
        super.onResume()

        val myContext = context

        if(myContext != null)
            mDataProvider.OnResume(myContext)

        val sectionObservable: Observable<Any> = Observables.combineLatest(mSectionListSubject, mPreferences.MapsDisplayModePreferenceObservable(mActivity)) {
            list, mapPreference ->

            if(mapPreference == Preferences.MapsDisplayModePreference.kTraditional)
                mBinding.mapList.setAdapter(TraditionalMapsSectionRecyclerViewAdapter(mDataProvider))
            else
                mBinding.mapList.setAdapter(MapsSectionRecyclerViewAdapter(mDataProvider))

            val items: ArrayList<Pair<MapsList.GroupSection, MapsList.Group>> = arrayListOf()

            list.values.forEach {section ->
                mGroup?.let {group ->
                    items.add(Pair(section, group))
                }
            }

            mBinding.mapList.SetItems(items)
        }

        val groupObservable = Observables.combineLatest(mGroupsListSubject, mPreferences.MapsDisplayModePreferenceObservable(mActivity)) {
            list, mapPreference ->

            if(mapPreference == Preferences.MapsDisplayModePreference.kTraditional)
                mBinding.mapList.setAdapter(TraditionalMapsGroupRecyclerViewAdapter(mActivity))
            else
                mBinding.mapList.setAdapter(MapsGroupRecyclerViewAdapter(mActivity, mDataProvider))

            mBinding.mapList.SetItems(ArrayList(list.GetMain()))
        }

        val mapListDataObservable: Observable<Any> = sectionObservable.mergeWith(groupObservable)

        mapListDataObservable.subscribe(object: Observer<Any>{
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {
                mDataAndDisplayModeDisposable?.dispose()
                mDataAndDisplayModeDisposable = d
            }
            override fun onNext(t: Any) {}
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }


    override fun onPause() {
        super.onPause()

        mDataProvider.OnPause()

        mDataAndDisplayModeDisposable?.dispose()
    }


    override fun onDestroyView() {
        mGroupListDataDisposable?.dispose()
        mGroupListDataDisposable = null
        mPageStateDisposable?.dispose()
        mPageStateDisposable = null

        super.onDestroyView()
    }
}
