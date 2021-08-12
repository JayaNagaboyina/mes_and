package org.mesonet.app.webview

import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebViewClient
import org.mesonet.app.R

import org.mesonet.app.databinding.WebViewActivityBinding
import java.util.*
import android.view.ViewGroup
import android.webkit.WebView


class WebViewActivity : AppCompatActivity() {
    var mPaused = true
    var mLastUpdate = 0L
    var mBinding: WebViewActivityBinding? = null

    var mAdapter: WebViewPagerAdapter? = null

    public override fun onCreate(inSavedInstanceState: Bundle?) {
        super.onCreate(inSavedInstanceState)
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.web_view_activity, null, false)

        var urls: List<String>? = null
        var titles: List<String>? = null
        var startIndex = 0

        mAdapter = WebViewPagerAdapter()

        if(intent.hasExtra(kUrls))
            urls = intent.getStringArrayListExtra(kUrls)

        if(intent.hasExtra(kTitles))
            titles = intent.getStringArrayListExtra(kTitles)

        if(intent.hasExtra(kStartIndex))
            startIndex = intent.getIntExtra(kStartIndex, 0)

        val pagerList = ArrayList<PagerData>()

        mBinding?.toolBar?.setNavigationIcon(R.drawable.ic_close_white_36dp)
        mBinding?.toolBar?.setNavigationOnClickListener { finish() }

        for(i in 0 until (urls?.size?: 0)) {
            var url = urls?.get(i)
            if (intent.getBooleanExtra(kUseGoogleDocs, false))
                url = "http://docs.google.com/gview?embedded=true&url=$url"

            pagerList.add(PagerData(titles?.get(i)?: "", url))
        }

        if (intent.getBooleanExtra(kAllowShare, false)) {
            mBinding?.shareButton?.show()
            mBinding?.shareButton?.setOnClickListener {
                it.isEnabled = false
                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plain"

                (mBinding?.viewPager?.adapter as WebViewPagerAdapter).mData?.let {data ->
                    if(data.isNotEmpty()) {
                        val selectedMap = data[mBinding?.viewPager?.currentItem ?: 0]
                        i.putExtra(Intent.EXTRA_SUBJECT, selectedMap.mTitle)
                        i.putExtra(Intent.EXTRA_TEXT, selectedMap.mUrl)
                    }
                }

                startActivity(Intent.createChooser(i, "Share URL"))
                it.isEnabled = true
            }
        }

        if (urls == null) {
            //if (intent.hasExtra(kRaw) && !intent.getStringExtra(kRaw).isBlank()) {
            if (intent.hasExtra(kRaw) && !intent.getStringExtra(kRaw)?.isBlank()!!) {
                var title = ""
                if(titles?.size?: 0 > 0)
                    title = titles?.get(0)?: ""

                mAdapter?.SetItems(listOf(PagerData(title, null)))
            }
        }
        else
            mAdapter?.SetItems(pagerList)

        mLastUpdate = Date().time

        mBinding?.viewPager?.adapter = mAdapter

        val pageChangeListener = object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(inScrollState: Int) {
            }
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(inPage: Int) {
                val title = (mBinding?.viewPager?.adapter as WebViewPagerAdapter).mData?.get(inPage)?.mTitle

                mBinding?.toolBar?.title = title
            }

        }

        mBinding?.viewPager?.addOnPageChangeListener(pageChangeListener)

        mBinding?.viewPager?.postDelayed({
                if(startIndex == 0)
                    pageChangeListener.onPageSelected(0)
                else
                    mBinding?.viewPager?.setCurrentItem(startIndex, false)


        }, 100)

        setContentView(mBinding?.root)
    }

    override fun onResume() {
        super.onResume()

        val updateInterval = intent.getLongExtra(kUpdateInterval, -1)

        if(mPaused) {
            mPaused = false
            if (updateInterval > -1) {
                Handler().post(object : Runnable {
                    override fun run() {
                        if (!mPaused) {
                            if(mLastUpdate - updateInterval < Date().time) {
                                (mBinding?.viewPager?.adapter as WebViewPagerAdapter).RefreshPages()
                                mLastUpdate = Date().time
                            }
                            Handler().postDelayed(this, updateInterval)
                        }
                    }
                })
            }
        }
    }

    override fun onPause() {
        mPaused = true

        super.onPause()
    }

    companion object {
        val kTitles = "titles"
        val kUrls = "urls"
        val kStartIndex = "startIndex"
        val kRaw = "raw"
        val kInitialZoom = "initialZoom"
        val kAllowUserZoom = "allowUserZoom"
        val kUseGoogleDocs = "useGoogleDocs"
        val kAllowShare = "allowShare"
        val kUpdateInterval = "updateInterval"
    }


    inner class WebViewPagerAdapter: androidx.viewpager.widget.PagerAdapter() {
        var mData: List<PagerData>? = null
        var mWebViews: ArrayList<WebView>? = null

        fun SetItems(pagerData: List<PagerData>) {
            mData = pagerData
            mWebViews = arrayListOf()
            notifyDataSetChanged()
        }

        override fun instantiateItem(collection: ViewGroup, position: Int): Any {
            val layout = layoutInflater.inflate(R.layout.web_view, collection, false)

            val webView = layout.findViewById<WebView>(R.id.webView)

            val allowUserZoom = intent.getBooleanExtra(kAllowUserZoom, false)

            val webSettings = webView.settings
            webSettings?.javaScriptEnabled = true
            webSettings.setSupportZoom(allowUserZoom)
            webSettings.builtInZoomControls = allowUserZoom
            webSettings.displayZoomControls = false
            webView.webViewClient = WebViewClient()

            val initialScale = intent.getIntExtra(kInitialZoom, -1)

            if (initialScale != -1) {
                webView.setInitialScale(initialScale)

                webView.settings?.loadWithOverviewMode = true
                webView.settings?.useWideViewPort = true
            }

            collection.addView(layout)

            mWebViews?.add(webView)

            mData?.get(position)?.mUrl?.let {
                webView.loadUrl(it)
                return layout
            }

            if(intent.hasExtra(kRaw)) {
            Log.d("WbViewActivity: ", "intent has extras, loadurl")
                webView.loadUrl(intent.getStringExtra(kRaw).toString())

            }

            return layout
        }

        fun RefreshPages() {
            mWebViews?.forEach { webView ->
                webView.reload()
            }
        }

        override fun destroyItem(inContainer: ViewGroup, inPosition: Int, inObject: Any) {
            inContainer.removeView(inObject as View)
        }

        override fun isViewFromObject(inView: View, inObject: Any): Boolean {
            return inView == inObject
        }

        override fun getCount(): Int {
            return mData?.size?: 0
        }
    }

    class PagerData (
        val mTitle: String,
        val mUrl: String?
    )
}
