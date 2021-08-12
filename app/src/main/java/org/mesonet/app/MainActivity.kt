package org.mesonet.app

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.navigation.NavigationView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.mesonet.app.advisories.AdvisoriesFragment
import org.mesonet.app.baseclasses.BaseActivity
import org.mesonet.app.contact.ContactActivity
import org.mesonet.app.databinding.MainActivityBinding
import org.mesonet.app.maps.MapListFragment
import org.mesonet.app.radar.RadarFragment
import org.mesonet.app.site.SiteOverviewFragment
import org.mesonet.app.usersettings.UserSettingsActivity
import org.mesonet.app.webview.WebViewActivity
import org.mesonet.core.PerContext
import org.mesonet.dataprocessing.ConnectivityStatusProvider
import org.mesonet.dataprocessing.LocationProvider
import org.mesonet.dataprocessing.advisories.AdvisoryDataProvider
import org.mesonet.dataprocessing.maps.MapsDataProvider
import org.mesonet.dataprocessing.radar.RadarImageDataProvider
import org.mesonet.dataprocessing.radar.RadarSiteDataProvider
import org.mesonet.dataprocessing.site.MesonetSiteDataController
import org.mesonet.dataprocessing.site.forecast.FiveDayForecastDataController
import org.mesonet.dataprocessing.site.mesonetdata.MesonetUIController
import org.mesonet.dataprocessing.userdata.Preferences
import org.mesonet.models.advisories.Advisory
import javax.inject.Inject


@PerContext
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener {
    private val kSelectedTabId = "selectedTabId"

    private var mBinding: MainActivityBinding? = null

    private var mActionBarDrawerToggle: ActionBarDrawerToggle? = null

    private var mLoadedFragmentId = R.id.mesonetOption

    private var mLoadedFragment: androidx.fragment.app.Fragment? = null
    private val MY_PERMISSION_REQUEST_CODE = 0x1


    @Inject
    internal lateinit var mAdvisoryDataProvider: AdvisoryDataProvider

    @Inject
    internal lateinit var mMesonetSiteDataController: MesonetSiteDataController

    @Inject
    internal lateinit var mMesonetUIController: MesonetUIController

    @Inject
    internal lateinit var mFiveDayForecastDataController: FiveDayForecastDataController

    @Inject
    internal lateinit var mMapsController: MapsDataProvider

    @Inject
    internal lateinit var mRadarSiteDataProvider: RadarSiteDataProvider

    @Inject
    internal lateinit var mRadarImageDataProvider: RadarImageDataProvider

    @Inject
    internal lateinit var mConnectivityStatusProvider: ConnectivityStatusProvider

    @Inject
    lateinit var mLocationProvider: LocationProvider

    @Inject
    lateinit var mPreferences: Preferences

    var mAdvisoryDisposable: Disposable? = null




    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mConnectivityStatusProvider.OnCreate(this)
        mFiveDayForecastDataController.OnCreate(this)
        mRadarImageDataProvider.OnCreate(this)

        var selectedTab = R.id.mesonetOption

        if (savedInstanceState != null)
            selectedTab = savedInstanceState.getInt(kSelectedTabId)

        LoadBinding(selectedTab)

        mMesonetSiteDataController.OnCreate(this)

        mMesonetUIController.OnCreate(this)

        mAdvisoryDataProvider.OnCreate(this)
        //Shared preference filed for permissions
       /* val prefs:SharedPreferences = this.getSharedPreferences("UserPerms", Context.MODE_PRIVATE)
        val spe = prefs.edit()
        if(!prefs.contains("phone_perm")) {
            Log.i("MainActivity", "pHone_perm not exist")
            spe.putBoolean("phone_perm", true)
            spe.commit()
        }*/
    }


    override fun onStart() {
        super.onStart()

        mLoadedFragment?.let {
            if(it is RadarFragment) {
                val newRadarFragment = RadarFragment()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragmentLayout, newRadarFragment)
                fragmentTransaction.commit()
                mLoadedFragment = newRadarFragment
            }
        }
    }


    override fun onResume() {
        super.onResume()
        mConnectivityStatusProvider.OnResume(this)
        mAdvisoryDataProvider.OnResume(this)

        if(mAdvisoryDisposable?.isDisposed != false) {
            mAdvisoryDataProvider.GetDataObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Advisory.AdvisoryList> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    mAdvisoryDisposable = d
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onNext(t: Advisory.AdvisoryList) {
                    if (t.isNotEmpty())
                        mBinding?.bottomNav?.menu?.getItem(3)?.icon = resources.getDrawable(R.drawable.advisory_badge, theme)
                    else
                        mBinding?.bottomNav?.menu?.getItem(3)?.setIcon(R.drawable.advisories_selector)
                }
            })
        }
    }


    override fun onPause() {
        mAdvisoryDisposable?.dispose()
        mAdvisoryDisposable = null

        mAdvisoryDataProvider.OnPause()
        mConnectivityStatusProvider.OnPause()

        super.onPause()
    }



    private fun LoadBinding(inSelectedTab: Int) {
        SetPage(inSelectedTab)

        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        mBinding?.toolBar?.inflateMenu(R.menu.ticker_menu)
        mBinding?.toolBar?.setOnMenuItemClickListener(this)

        setSupportActionBar(mBinding?.toolBar)

        val actionBar = supportActionBar

        if (actionBar != null) {
            mActionBarDrawerToggle = object : ActionBarDrawerToggle(this, mBinding?.drawer, mBinding?.toolBar, R.string.app_name, R.string.app_name) {
                override fun onDrawerClosed(drawerView: View) {
                    syncState()
                }

                override fun onDrawerOpened(drawerView: View) {
                    syncState()
                }
            }

            mActionBarDrawerToggle?.isDrawerIndicatorEnabled = true
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)

            mBinding?.drawer?.addDrawerListener(mActionBarDrawerToggle
                    ?: object : androidx.drawerlayout.widget.DrawerLayout.DrawerListener {
                        override fun onDrawerStateChanged(p0: Int) {

                        }

                        override fun onDrawerSlide(p0: View, p1: Float) {

                        }

                        override fun onDrawerClosed(p0: View) {

                        }

                        override fun onDrawerOpened(p0: View) {

                        }
                    })
            mBinding?.drawerNavView?.setNavigationItemSelectedListener(this)
            mActionBarDrawerToggle?.syncState()
        }

        mBinding?.bottomNav?.setOnNavigationItemSelectedListener { inItem ->
            SetPage(inItem.itemId)
            true
        }

        mBinding?.bottomNav?.itemIconTintList = null

        val menuView = mBinding?.bottomNav?.getChildAt(0) as BottomNavigationMenuView


        for (i in 0 until (mBinding?.bottomNav?.menu?.size()?: 0)) {
            //val iconView = menuView.getChildAt(i).findViewById<View>(android.support.design.R.id.icon)
            val iconView = menuView.getChildAt(i).findViewById<View>(com.google.android.material.R.id.icon)
            val layoutParams = iconView.layoutParams
            val displayMetrics = resources.displayMetrics
            layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32f, displayMetrics).toInt()
            layoutParams.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32f, displayMetrics).toInt()
            iconView.layoutParams = layoutParams
        }
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        mBinding?.bottomNav?.selectedItemId = mLoadedFragmentId
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(kSelectedTabId, mBinding?.bottomNav?.selectedItemId ?: 0)
        super.onSaveInstanceState(outState)
    }


    private fun SetPage(inSelectedTab: Int) {

       /* if (inSelectedTab == R.id.radarOption && Build.VERSION.SDK_INT > 29) {
                if (checkPhoneStatePermission()) {
                    mLoadedFragment = RadarFragment()
                    mLoadedFragmentId = inSelectedTab
                    mLoadedFragment?.let {
                        val fragmentTransaction = supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.fragmentLayout, it)
                        fragmentTransaction.commit()
                    }
                } else {
                        Toast.makeText(this, "Permission to read Phone State is required to get Radar map", Toast.LENGTH_SHORT).show()
                        Thread.sleep(400)
                        requestPhoneStatePerm()
                }
        } else {*/
            //Log.d("MainActivity", Build.VERSION.SDK_INT.toString())
            //Log.d("MainActivity", Build.VERSION_CODES.CUR_DEVELOPMENT.toString() + Build.VERSION.CODENAME)
            when (inSelectedTab) {

                R.id.mesonetOption -> mLoadedFragment = SiteOverviewFragment()

                R.id.mapsOption -> mLoadedFragment = MapListFragment()

                R.id.radarOption -> mLoadedFragment = RadarFragment()

                R.id.advisoriesOption -> mLoadedFragment = AdvisoriesFragment()

            }
            mLoadedFragmentId = inSelectedTab
            mLoadedFragment?.let {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragmentLayout, it)
                fragmentTransaction.commit()
            }
        //}
    }

    // Adding Telemetry 6.1.0 fixed the crash issue so no need to ask pernission.
    /* READ_PHONE_STATE permission for mapbox's getDataNetworktype(), if no permission available app crashes on click of radar option*/
    /*private fun checkPhoneStatePermission(): Boolean {

        val hasPhoneStatePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED

        return hasPhoneStatePerm
    }

    private fun requestPhoneStatePerm() {
        val hasPhoneStatePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
        if(!hasPhoneStatePerm)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {

                //val alertBuilder = AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
                val alertBuilder = AlertDialog.Builder(this, R.style.myAlertDialog)
                alertBuilder.setTitle("Permission")
                alertBuilder.setMessage("Mapbox need the phone permission to get the radar from network only but will not manage/make any phone calls.")
                alertBuilder.setPositiveButton(android.R.string.ok) { dialog, which ->
                    dialog?.cancel()
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf<String>(Manifest.permission.READ_PHONE_STATE),
                            MY_PERMISSION_REQUEST_CODE)
                }
                alertBuilder.setNegativeButton("cancel") { dialog, which -> dialog?.cancel() }
                val alert: AlertDialog = alertBuilder.create()
                alert.setCancelable(false)
                alert.show()
            } else {
                var firstTimeAsking:Boolean = true
                val prefs:SharedPreferences = this.getSharedPreferences("UserPerms", Context.MODE_PRIVATE)
                val spe = prefs.edit()
                if(prefs.contains("phone_perm")) {
                    firstTimeAsking = prefs.getBoolean("phone_perm", true)
                }
                if(firstTimeAsking) {
                    //First time -  No explanation needed, we can request the permission.
                    spe.putBoolean("phone_perm", false)
                    spe.commit()
                    Log.i("MainActivity", "first time permission request")
                    ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.READ_PHONE_STATE),
                            MY_PERMISSION_REQUEST_CODE)
                } else {
                    val alertBuilder = AlertDialog.Builder(this, R.style.myAlertDialog)
                    alertBuilder.setTitle("Permission")
                    alertBuilder.setMessage("You denied the phone state Permission. Would like to change the permission in settings?")
                    alertBuilder.setPositiveButton("goto settings") { dialog, which ->
                        dialog?.cancel()
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                    alertBuilder.setNegativeButton("cancel") { dialog, which -> dialog?.cancel() }
                    val alert: AlertDialog = alertBuilder.create()
                    alert.setCancelable(false)
                    alert.show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(inRequestCode: Int, inPermissions: Array<String>, inGrantResults: IntArray) {
        super.onRequestPermissionsResult(inRequestCode, inPermissions, inGrantResults)
        if(inRequestCode == MY_PERMISSION_REQUEST_CODE && inGrantResults.isNotEmpty() ) {
            if(inGrantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("MainActivity: onResult ", "READ_PHONE_STATE Permission granted")
                SetPage(R.id.radarOption)
            } else if(inGrantResults[0] == PackageManager.PERMISSION_DENIED) {
                Log.d("MainActivity: onResult ", "READ_PHONE_STATE Permission denied")
                val toast = Toast.makeText(this, "Can not load Radar as Permission to read Phone State is denied", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.START, 150, 100)
                toast.show()
                //requestPhoneStatePerm()
            }
        }
    }*/

    fun CloseKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mBinding?.root?.windowToken, 0)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.ticker_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.isEnabled = false
        val intent = Intent(baseContext, WebViewActivity::class.java)
        intent.putStringArrayListExtra(WebViewActivity.kTitles, arrayListOf("Ticker"))
        intent.putStringArrayListExtra(WebViewActivity.kUrls, arrayListOf("http://www.mesonet.org/index.php/app/ticker_article/latest.ticker.txt"))
        startActivity(intent)
        item.isEnabled = true

        return super.onOptionsItemSelected(item)
    }


    public override fun onDestroy() {
        mBinding?.drawer?.removeDrawerListener(mActionBarDrawerToggle
                ?: object : androidx.drawerlayout.widget.DrawerLayout.DrawerListener {
                    override fun onDrawerStateChanged(p0: Int) {
                    }

                    override fun onDrawerSlide(p0: View, p1: Float) {
                    }

                    override fun onDrawerClosed(p0: View) {
                    }

                    override fun onDrawerOpened(p0: View) {
                    }

                })

        mActionBarDrawerToggle = null

        mMesonetSiteDataController.OnDestroy()
        mMesonetUIController.OnDestroy()
        mFiveDayForecastDataController.OnDestroy()
        mMapsController.OnDestroy()
        mAdvisoryDataProvider.OnDestroy()
        mRadarSiteDataProvider.Dispose()
        mRadarImageDataProvider.OnDestroy()
        mConnectivityStatusProvider.OnDestroy()
        mPreferences.Dispose()

        super.onDestroy()
    }


    override fun onNavigationItemSelected(inMenuItem: MenuItem): Boolean {
        inMenuItem.isEnabled = false
        when (inMenuItem.itemId) {
            R.id.userSettings -> {
                val userSettingsIntent = Intent(baseContext, UserSettingsActivity::class.java)

                startActivity(userSettingsIntent)
            }
            R.id.contact -> {
                val contactIntent = Intent(baseContext, ContactActivity::class.java)

                startActivity(contactIntent)
            }
            R.id.about -> {
                val intent = Intent(baseContext, WebViewActivity::class.java)
                intent.putStringArrayListExtra(WebViewActivity.kTitles, arrayListOf(getString(R.string.AboutAndTermsOfUse)))
                intent.putExtra(WebViewActivity.kRaw, "file:///android_res/raw/about.html")
                startActivity(intent)
            }
        }

        //mBinding?.drawer?.closeDrawer(Gravity.START)
        mBinding?.drawer?.closeDrawer(Gravity.LEFT)

        inMenuItem.isEnabled = true
        return false
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        item.isEnabled = false
        val intent = Intent(baseContext, WebViewActivity::class.java)
        intent.putStringArrayListExtra(WebViewActivity.kTitles, arrayListOf("Ticker"))
        intent.putStringArrayListExtra(WebViewActivity.kUrls, arrayListOf("http://www.mesonet.org/index.php/app/ticker_article/latest.ticker.txt"))
        startActivity(intent)
        item.isEnabled = true

        return false
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == mPermissions.LocationRequestCode())
        {
            mLocationProvider.RegisterGpsResult(resultCode)
        }
    }


    override fun NavigateToPage(inFragment: androidx.fragment.app.Fragment, inPushToBackStack: Boolean, @AnimRes inAnimationIn: Int, @AnimRes inAnimationOut: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (inAnimationIn != 0 || inAnimationOut != 0)
            fragmentTransaction.setCustomAnimations(inAnimationIn, inAnimationOut)

        fragmentTransaction.replace(R.id.fragmentLayout, inFragment)

        if (inPushToBackStack)
            fragmentTransaction.addToBackStack(inFragment.javaClass.name)
        fragmentTransaction.commit()
    }
}