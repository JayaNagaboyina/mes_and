package org.mesonet.app.site

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import org.mesonet.app.R

import org.mesonet.app.baseclasses.BaseFragment
import org.mesonet.app.databinding.MesonetDataContainerBinding
import org.mesonet.app.webview.WebViewActivity
import org.mesonet.app.databinding.SiteOverviewFragmentBinding
import org.mesonet.app.filterlist.FilterListFragment
import org.mesonet.app.site.forecast.ForecastListView
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.dataprocessing.site.MesonetSiteDataController
import org.mesonet.dataprocessing.site.mesonetdata.MesonetUIController
import org.mesonet.dataprocessing.site.forecast.FiveDayForecastDataController
import org.mesonet.dataprocessing.site.forecast.SemiDayForecastDataController
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

import javax.inject.Inject


class SiteOverviewFragment : BaseFragment(), FilterListFragment.FilterListCloser, Toolbar.OnMenuItemClickListener, View.OnClickListener, Observer<MesonetSiteDataController.ProcessedMesonetSite> {
    private var mMesonetDataBinding: MesonetDataContainerBinding? = null

    private var mBinding: SiteOverviewFragmentBinding? = null

    @Inject
    internal lateinit var mFiveDayForecastDataController: FiveDayForecastDataController

    @Inject
    internal lateinit var mMesonetUIController: MesonetUIController

    @Inject
    internal lateinit var mMesonetSiteDataController: MesonetSiteDataController

    var mMesonetDisposable: Disposable? = null
    var mForecastDisposable: Disposable? = null
    var mMesonetPageStateDisposable: Disposable? = null
    var mForecastPageStateDisposable: Disposable? = null

    private val mSyncObject = Object()

    internal var mCurrentStid: String? = null

    private var mContinueUpdates = AtomicBoolean(false)

    override fun onCreateView(inInflater: LayoutInflater, inParent: ViewGroup?, inSavedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inInflater, R.layout.site_overview_fragment, inParent, false)

        val myContext = context

        if(myContext != null)
            mMesonetSiteDataController.GetCurrentSelectionObservable().distinctUntilChanged{site -> site.GetStid()}.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(this)

        mBinding?.previousFab?.setOnClickListener {
            val currentSelected = mBinding?.forecastViewPager?.currentItem ?: 0

            if (currentSelected > 0)
                mBinding?.forecastViewPager?.currentItem = currentSelected - 1
        }

        mBinding?.nextFab?.setOnClickListener {
            val currentSelected = mBinding?.forecastViewPager?.currentItem ?: 0

            if (currentSelected < ((mBinding?.forecastViewPager?.childCount ?: 0) - 1))
                mBinding?.forecastViewPager?.currentItem = currentSelected + 1
        }

        mBinding?.forecastViewPager?.offscreenPageLimit = 2
        mBinding?.forecastViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                CalculateScrollButtonVisibility()
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })

        CalculateScrollButtonVisibility()

        return mBinding?.root ?: View(context)
    }


    private fun CalculateScrollButtonVisibility() {
        if(mBinding != null && mBinding?.forecastViewPager?.adapter != null) {
            if (mBinding?.forecastViewPager?.currentItem == 0)
                mBinding?.previousFab?.visibility = View.GONE
            else
                mBinding?.previousFab?.visibility = View.VISIBLE

            if ((mBinding?.forecastViewPager?.currentItem ?: 0) < ((mBinding?.forecastViewPager?.adapter?.count ?: 0) - 1))
                mBinding?.nextFab?.visibility = View.VISIBLE
            else
                mBinding?.nextFab?.visibility = View.GONE
        }
    }


    private fun RevealView(view: View?) {
        val cx = (view?.left ?: 0) + (view?.right ?: 0) - 24
        val cy = (view?.top ?: 0) + (view?.bottom ?: 0) / 2
        val radius = Math.max(mBinding?.childFragmentContainer?.width ?: 0, mBinding?.childFragmentContainer?.height ?: 0) * 2.0f

        if (mBinding?.childFragmentContainer?.visibility != View.VISIBLE) {

            mBinding?.childFragmentContainer?.visibility = View.VISIBLE
            val hide = ViewAnimationUtils.createCircularReveal(mBinding?.childFragmentContainer, cx, cy, 0f, radius)
            hide.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mBinding?.childFragmentContainer?.visibility = View.VISIBLE
                    hide.removeListener(this)
                }
            })
            hide.start()

        } else {
            val reveal = ViewAnimationUtils.createCircularReveal(
                    mBinding?.childFragmentContainer, cx, cy, radius, 0f)
            reveal.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mBinding?.childFragmentContainer?.visibility = View.INVISIBLE
                    reveal.removeListener(this)
                }
            })
            reveal.start()
        }
    }


    override fun Close() {
        Observable.create(ObservableOnSubscribe<Void> {
            RevealView(mMesonetDataBinding?.siteToolbar)
            it.onComplete()
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<Void> {
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: Void) {}
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }

    override fun onMenuItemClick(menuItem: MenuItem): Boolean {
        menuItem.isEnabled = false
        when (menuItem.itemId) {
            R.id.openList -> {
                OpenSiteMenu()
            }
            R.id.favorite -> ToggleFavorite()
        }
        menuItem.isEnabled = true
        return false
    }


    override fun onClick(v: View?) {
        OpenSiteMenu()
    }


    private fun OpenSiteMenu() {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.childFragmentContainer, FilterListFragment())
        transaction.commit()
        RevealView(mMesonetDataBinding?.siteToolbar)
    }



    private fun ToggleFavorite() {
        mMesonetSiteDataController.ToggleFavorite(mMesonetSiteDataController.CurrentSelection()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Boolean> {
            var disposable: Disposable? = null
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable)
            {
                disposable = d
            }
            override fun onNext(t: Boolean) {
                if (t) {
                    mMesonetDataBinding?.siteToolbar?.menu?.findItem(R.id.favorite)?.setIcon(R.drawable.ic_favorite_white_36dp)
                } else {
                    mMesonetDataBinding?.siteToolbar?.menu?.findItem(R.id.favorite)?.setIcon(R.drawable.ic_favorite_border_white_36dp)
                }
                disposable?.dispose()
                disposable = null
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })
    }


    override fun onResume() {
        super.onResume()

        mMesonetUIController.GetPageStateObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<PageStateInfo>{
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {
                mMesonetPageStateDisposable = d
            }

            override fun onNext(t: PageStateInfo) {
                when(t.GetPageState())
                {
                    PageStateInfo.PageState.kLoading -> {
                        mMesonetDataBinding?.mesonetData?.mesonetLayout?.visibility = View.GONE
                        mMesonetDataBinding?.mesonetErrorText?.visibility = View.GONE
                        mMesonetDataBinding?.mesonetErrorText?.text = ""
                        mBinding?.mesonetProgressBar?.visibility = View.VISIBLE
                    }

                    PageStateInfo.PageState.kError -> {
                        mMesonetDataBinding?.mesonetData?.mesonetLayout?.visibility = View.GONE
                        mBinding?.mesonetProgressBar?.visibility = View.GONE
                        mMesonetDataBinding?.mesonetErrorText?.text = t.GetErrorMessage()
                        mMesonetDataBinding?.mesonetErrorText?.visibility = View.VISIBLE
                    }

                    PageStateInfo.PageState.kData -> {
                        mBinding?.mesonetProgressBar?.visibility = View.GONE
                        mMesonetDataBinding?.mesonetErrorText?.visibility = View.GONE
                        mMesonetDataBinding?.mesonetErrorText?.text = ""
                        mMesonetDataBinding?.mesonetData?.mesonetLayout?.visibility = View.VISIBLE
                        mBinding?.invalidateAll()
                        mMesonetDataBinding?.invalidateAll()
                    }
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })

        mFiveDayForecastDataController.GetPageStateObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<PageStateInfo>{
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {
                mForecastPageStateDisposable = d
            }

            override fun onNext(t: PageStateInfo) {
                when(t.GetPageState())
                {
                    PageStateInfo.PageState.kLoading -> {
                        mBinding?.forecastViewPager?.visibility = View.GONE
                        mBinding?.forecastErrorText?.visibility = View.GONE
                        mBinding?.forecastProgressBar?.visibility = View.VISIBLE
                    }

                    PageStateInfo.PageState.kError -> {
                        mBinding?.forecastProgressBar?.visibility = View.GONE
                        mBinding?.forecastViewPager?.visibility = View.GONE
                        mBinding?.forecastErrorText?.text = t.GetErrorMessage()
                        mBinding?.forecastErrorText?.visibility = View.VISIBLE
                    }

                    PageStateInfo.PageState.kData -> {
                        mBinding?.forecastProgressBar?.visibility = View.GONE
                        mBinding?.forecastErrorText?.text = ""
                        mBinding?.forecastErrorText?.visibility = View.GONE
                        mBinding?.forecastViewPager?.visibility = View.VISIBLE
                        mBinding?.invalidateAll()
                    }
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })

        val myContext = context

        mContinueUpdates.set(true)

        if(myContext != null) {
            mMesonetUIController.OnResume(myContext)
            mFiveDayForecastDataController.OnResume(myContext)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            SetMesonetBinding(mBinding?.mesonetDataContainer)

        if(myContext != null)
        mFiveDayForecastDataController.GetForecastDataSubject(myContext).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<List<SemiDayForecastDataController>> {
            override fun onComplete() {}

            override fun onSubscribe(d: Disposable) {
                mForecastDisposable = d
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()

                mBinding?.forecastViewPager?.visibility = View.GONE
                mBinding?.forecastErrorText?.visibility = View.VISIBLE
            }

            override fun onNext(t: List<SemiDayForecastDataController>) {
                if (mBinding?.forecastViewPager?.adapter == null || mBinding?.forecastViewPager?.adapter?.count == 0) {
                    val forecastsPerPage = resources.getInteger(R.integer.forecastsPerPage)
                    var pageCount = mFiveDayForecastDataController.GetCount() / forecastsPerPage

                    val forecastPages = ArrayList<ForecastListView>()
                    var i = 0
                    while (i < mFiveDayForecastDataController.GetCount() && i < (pageCount * forecastsPerPage)) {
                        val forecastListView = ForecastListView(activity ?: Activity())

                        var j = 0
                        while (j < forecastsPerPage) {
                            forecastListView.SetSemiDayForecast(i + j, mFiveDayForecastDataController.GetForecast(i + j))
                            j++
                        }

                        forecastPages.add(forecastListView)
                        i += forecastsPerPage
                    }

                    if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        pageCount++
                    }

                    val finalPageCount = pageCount

                    if (mBinding?.forecastViewPager?.childCount == 0) {
                        val pagerAdapter = object : androidx.viewpager.widget.PagerAdapter() {
                            override fun getCount(): Int {
                                return finalPageCount
                            }

                            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                                return view == `object`
                            }

                            override fun instantiateItem(inViewGroup: ViewGroup, inPosition: Int): View {
                                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                                    mBinding?.forecastProgressBar?.visibility = View.GONE
                                var forecastPostion = inPosition
                                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                    if (inPosition == 0) {
                                        val mesonetBinding = DataBindingUtil.inflate<MesonetDataContainerBinding>(LayoutInflater.from(context), R.layout.mesonet_data_container, null, false)
                                        SetMesonetBinding(mesonetBinding)
                                        inViewGroup.addView(mesonetBinding.root)
                                        return mesonetBinding.root
                                    }

                                    forecastPostion--
                                }

                                if (forecastPages[forecastPostion].parent != null)
                                    (forecastPages[forecastPostion].parent as ViewGroup).removeView(forecastPages[forecastPostion])
                                inViewGroup.addView(forecastPages[forecastPostion])
                                return forecastPages[forecastPostion]
                            }

                            override fun destroyItem(inParent: ViewGroup, inPosition: Int, inObject: Any) {}
                        }

                        mBinding?.forecastViewPager?.adapter = pagerAdapter
                        mBinding?.forecastViewPager?.adapter?.notifyDataSetChanged()
                    }

                    mBinding?.forecastProgressBar?.visibility = View.GONE
                    mBinding?.forecastViewPager?.visibility = View.VISIBLE
                }
            }
        })
    }


    override fun onPause() {
        mForecastDisposable?.dispose()
        mForecastDisposable = null
        mMesonetDisposable?.dispose()
        mMesonetDisposable = null
        mMesonetPageStateDisposable?.dispose()
        mMesonetPageStateDisposable = null
        mForecastDisposable?.dispose()
        mForecastDisposable = null

        mMesonetUIController.OnPause()
        mFiveDayForecastDataController.OnPause()

        mContinueUpdates.set(true)

        super.onPause()
    }


    override fun onDestroyView() {
        for(i in 0..(mBinding?.forecastViewPager?.childCount?: 0 - 1))
        {
            if(mBinding?.forecastViewPager?.getChildAt(i) is ForecastListView)
                (mBinding?.forecastViewPager?.getChildAt(i) as ForecastListView).Dispose()
        }

        mDisposable?.dispose()
        mDisposable = null

        super.onDestroyView()
    }

    private var mDisposable: Disposable? = null

    override fun onComplete() {}
    override fun onSubscribe(d: Disposable)
    {
        mDisposable = d
    }
    override fun onError(e: Throwable)
    {
        e.printStackTrace()
    }

    override fun onNext(t: MesonetSiteDataController.ProcessedMesonetSite) {
        if (isAdded && t.GetStid() != mCurrentStid) {
            mMesonetDataBinding?.siteToolbar?.title = ""
        }
    }


    private fun SetMesonetBinding(inContainerBinding: MesonetDataContainerBinding?) {
        mMesonetDataBinding = inContainerBinding

        synchronized(mSyncObject)
        {
            if (mMesonetDisposable?.isDisposed != false) {
                Observables.combineLatest(
                        mMesonetSiteDataController.GetCurrentSelectionObservable().distinctUntilChanged { site -> site.GetStid() },
                        mMesonetUIController.GetDisplayFieldsObservable().observeOn(AndroidSchedulers.mainThread()))
                { site, data ->
                    Pair(site, data)
                }.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Pair<MesonetSiteDataController.ProcessedMesonetSite, MesonetUIController.MesonetDisplayFields>> {
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {
                        mMesonetDisposable = d
                    }

                    override fun onNext(t: Pair<MesonetSiteDataController.ProcessedMesonetSite, MesonetUIController.MesonetDisplayFields>) {
                        mMesonetDataBinding?.displayFields = t.second

                        mMesonetDataBinding?.siteToolbar?.title = t.first.GetName() ?: ""

                        if (mMesonetDataBinding?.siteToolbar?.menu?.hasVisibleItems() != true) {
                            mMesonetDataBinding?.siteToolbar?.inflateMenu(R.menu.mesonet_site_menu)
                        }

                        mMesonetDataBinding?.siteToolbar?.setOnClickListener(this@SiteOverviewFragment)
                        mMesonetDataBinding?.siteToolbar?.setOnMenuItemClickListener(this@SiteOverviewFragment)
                        mMesonetDataBinding?.siteToolbar?.setNavigationIcon(R.drawable.ic_multiline_chart_white_36dp)
                        mMesonetDataBinding?.siteToolbar?.setNavigationOnClickListener {
                            it.isEnabled = false
                            if (mMesonetSiteDataController.CurrentSelection() != "") {
                                val intent = Intent(activity?.baseContext, WebViewActivity::class.java)
                                intent.putStringArrayListExtra(WebViewActivity.kTitles, arrayListOf(t.first.GetName() + " Meteogram"))
                                intent.putStringArrayListExtra(WebViewActivity.kUrls, arrayListOf(t.first.GetMeteogramUrl()))
                                intent.putExtra(WebViewActivity.kInitialZoom, 1)
                                intent.putExtra(WebViewActivity.kAllowUserZoom, true)
                                intent.putExtra(WebViewActivity.kAllowShare, true)
                                intent.putExtra(WebViewActivity.kUpdateInterval, 150000L)
                                startActivity(intent)
                            }

                            it.isEnabled = true
                        }
                        if (t.first.IsFavorite()) {
                            mMesonetDataBinding?.siteToolbar?.menu?.findItem(R.id.favorite)?.setIcon(R.drawable.ic_favorite_white_36dp)
                        } else {
                            mMesonetDataBinding?.siteToolbar?.menu?.findItem(R.id.favorite)?.setIcon(R.drawable.ic_favorite_border_white_36dp)
                        }

                        mBinding?.invalidateAll()
                        mMesonetDataBinding?.invalidateAll()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
            }
        }
    }
}