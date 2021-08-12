package org.mesonet.app.radar

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Configuration
import androidx.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.*
import android.widget.SeekBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngQuad
import com.mapbox.mapboxsdk.maps.MapboxMapOptions
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.maps.SupportMapFragment
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.layers.RasterLayer
import com.mapbox.mapboxsdk.style.sources.ImageSource
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import org.mesonet.app.R

import org.mesonet.app.baseclasses.BaseFragment
import org.mesonet.app.databinding.RadarMapFragmentBinding
import org.mesonet.app.filterlist.FilterListFragment
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.dataprocessing.radar.MapboxMapController
import org.mesonet.dataprocessing.radar.RadarDataController
import org.mesonet.dataprocessing.radar.RadarImageDataProvider
import org.mesonet.dataprocessing.userdata.Preferences
import org.mesonet.models.radar.RadarDetails

import javax.inject.Inject
import kotlin.math.roundToInt


class RadarFragment : BaseFragment(), FilterListFragment.FilterListCloser {

    private val kRasterImageName = "Radar"
    private val kLightThemeUrl = "mapbox://styles/okmesonet/cjkmvlllh7oo12rplp8zufopc"
    private val kDarkThemeUrl = "mapbox://styles/okmesonet/cjkjtvw1n0nnm2rpen1ggwh99"

    @Inject
    internal lateinit var mRadarImageDataProvider: RadarImageDataProvider

    @Inject
    internal lateinit var mPreferences: Preferences

    @Inject
    internal lateinit var mMapController: MapboxMapController

    @Inject
    internal lateinit var mActivity: Activity

    private var mTransparencyDisposable: Disposable? = null
    private var mSelectedRadarDisposable: Disposable? = null
    private var mActiveImageDisposable: Disposable? = null
    private var mImageListDisposable: Disposable? = null
    private var mCameraUpdateDisposable: Disposable? = null
    private var mPageStateDisposable: Disposable? = null

    private var mEmtpyImage = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8)

    private var mBinding: RadarMapFragmentBinding? = null

    private var mMapFragment: SupportMapFragment? = null

    private var mRadarLayer: RasterLayer? = null
    private var mRadarImageSource: ImageSource? = null

    private var mLastRadarUpdated = ""
    private var mLastColorTheme = Preferences.RadarColorThemePreference.kLight

    private var mHoldPlay = false
    private var mState = PageStateInfo.PageState.kLoading


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMapController.GetCameraPositionObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<CameraPosition>
        {
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable)
            {
                mCameraUpdateDisposable = d
            }

            override fun onNext(t: CameraPosition) {
                mMapFragment?.getMapAsync { map ->
                    map.cameraPosition = t
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })

    }


    override fun onCreateView(inInflater: LayoutInflater, inContainer: ViewGroup?, inSavedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inInflater, R.layout.radar_map_fragment, inContainer, false)

        context?.let { fragmentContext ->
            val options = MapboxMapOptions.createFromAttributes(fragmentContext)
            options.textureMode(true)
            options.logoGravity(Gravity.TOP or Gravity.START)
            options.attributionGravity(Gravity.TOP or Gravity.START)

            mMapFragment = SupportMapFragment.newInstance(options)

            mRadarImageSource = ImageSource(kRasterImageName, LatLngQuad(LatLng(0.0, 0.0),
                    LatLng(0.0, 0.0),
                    LatLng(0.0, 0.0),
                    LatLng(0.0, 0.0)), R.drawable.blank_drawable)


            mRadarLayer = RasterLayer(kRasterImageName, mRadarImageSource?.id)

            mMapFragment?.let {
                val mapTransaction = childFragmentManager.beginTransaction()
                mapTransaction.add(R.id.mapContainer, it)
                mapTransaction.commit()
            }

            mBinding?.playPauseButton?.SetPlayPauseState(mMapController.GetPlayPauseStateObservable())

            mBinding?.playPauseButton?.setOnClickListener {
                mMapController.TogglePlay()
            }

            mBinding?.readingInfoLayout?.setOnClickListener {
                val filterTransaction = childFragmentManager.beginTransaction()
                filterTransaction.replace(R.id.childFragmentContainer, FilterListFragment())
                filterTransaction.commit()
                RevealView(mBinding?.readingInfoLayout, mBinding?.childFragmentContainer)
                (mBinding?.playPauseButton as View?)?.visibility = View.GONE
            }

            mBinding?.opacityDrawerButtonLayout?.setOnClickListener {
                RevealView(mBinding?.opacityDrawerButtonLayout, mBinding?.opacityLayout)
            }

            mBinding?.opacityCloseButton?.setOnClickListener {
                RevealView(mBinding?.opacityDrawerButtonLayout, mBinding?.opacityLayout)
            }

            mBinding?.transparencySeekBar?.max = 255

            mMapController.GetTransparencySubject().observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Float> {
                var disposable: Disposable? = null
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Float) {
                    mBinding?.transparencySeekBar?.progress = (t * 255.0f).roundToInt()
                    disposable?.dispose()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })

            mBinding?.transparencySeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(inSeekBar: SeekBar, inProgress: Int, inFromUser: Boolean) {
                    val result = inProgress / 255.0f

                    val subject = mMapController.GetTransparencySubject()

                    if (!subject.hasValue() || subject.value != result)
                        mMapController.GetTransparencySubject().onNext(result)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {

                }
            })
        }

        return mBinding?.root?: View(context)
    }


    override fun onResume() {
        super.onResume()

        val myContext = context

        if(myContext != null)
            mRadarImageDataProvider.OnResume(myContext)

        if(mPageStateDisposable?.isDisposed != false) {
            mRadarImageDataProvider.GetPageStateObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<PageStateInfo> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {
                    mPageStateDisposable = d
                }

                override fun onNext(t: PageStateInfo) {
                    mState = t.GetPageState()
                    when (t.GetPageState()) {
                        PageStateInfo.PageState.kLoading -> {
                            mTransparencyDisposable?.dispose()
                            mSelectedRadarDisposable?.dispose()
                            mRadarLayer?.setProperties(PropertyFactory.rasterOpacity(0.0f))
                            (mBinding?.playPauseButton as View?)?.visibility = View.GONE
                            mBinding?.readingInfoLayout?.visibility = View.GONE
                        }
                        PageStateInfo.PageState.kError -> {
                            mTransparencyDisposable?.dispose()
                            mSelectedRadarDisposable?.dispose()
                            mRadarLayer?.setProperties(PropertyFactory.rasterOpacity(0.0f))
                            (mBinding?.playPauseButton as View?)?.visibility = View.GONE
                            val errorMessage = t.GetErrorMessage()

                            if (errorMessage != null) {
                                mBinding?.readingInfoTextView?.text = errorMessage
                                mBinding?.readingInfoLayout?.visibility = View.VISIBLE
                            } else
                                mBinding?.readingInfoLayout?.visibility = View.GONE
                        }
                        PageStateInfo.PageState.kData -> {
                            mMapController.GetTransparencySubject().observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Float> {
                                override fun onComplete() {}
                                override fun onSubscribe(d: Disposable) {
                                    mTransparencyDisposable?.dispose()
                                    mTransparencyDisposable = d
                                }

                                override fun onNext(t: Float) {
                                    mRadarLayer?.setProperties(PropertyFactory.rasterOpacity(t))
                                }

                                override fun onError(e: Throwable) {
                                    onNext(0.0f)
                                    e.printStackTrace()
                                }
                            })

                            Observables.combineLatest(mRadarImageDataProvider.GetSiteInfoObservable(),
                                                    mPreferences.RadarColorThemePreferenceObservable(mActivity)).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Pair<Map.Entry<String, RadarDetails>, Preferences.RadarColorThemePreference>> {
                                override fun onComplete() {}

                                override fun onSubscribe(d: Disposable) {
                                    mSelectedRadarDisposable?.dispose()
                                    mSelectedRadarDisposable = d
                                }

                                override fun onError(e: Throwable) {
                                    e.printStackTrace()

                                    SetSnackbarText("Error", "", "")
                                }

                                override fun onNext(radarInfo: Pair<Map.Entry<String, RadarDetails>, Preferences.RadarColorThemePreference>) {
                                    if (mLastRadarUpdated != radarInfo.first.key || mLastColorTheme != radarInfo.second) {

                                        mLastRadarUpdated = radarInfo.first.key
                                        mLastColorTheme = radarInfo.second

                                        mHoldPlay = true

                                        if(mState == PageStateInfo.PageState.kData) {
                                            SetSnackbarText(radarInfo.first.key, radarInfo.first.value.GetName(), "")
                                            mBinding?.readingInfoLayout?.visibility = View.VISIBLE
                                        }

                                        mMapFragment?.getMapAsync { map ->

                                            if(map.style == null) {
                                                map.uiSettings.attributionDialogManager
                                                map.uiSettings.isRotateGesturesEnabled = false
                                                map.uiSettings.isTiltGesturesEnabled = false

                                                var styleUrl = kLightThemeUrl

                                                if (radarInfo.second == Preferences.RadarColorThemePreference.kDark)
                                                    styleUrl = kDarkThemeUrl

                                                map.setStyle(Style.Builder().fromUri(styleUrl))
                                            }

                                            map.getStyle() { style ->
                                                val sourceId = mRadarImageSource?.id ?: ""
                                                val layerId = mRadarLayer?.id ?: ""

                                                val radarImageSource = mRadarImageSource
                                                val radarLayer = mRadarLayer

                                                if (sourceId.isNotEmpty() && style.getSource(sourceId) == null && radarImageSource != null)
                                                    style.addSource(radarImageSource)
                                                if (radarInfo.first.key.isNotEmpty() && style.getLayer(layerId) == null && radarLayer != null)
                                                    style.addLayer(radarLayer)
                                                mRadarImageSource?.setCoordinates(LatLngQuad(LatLng(radarInfo.first.value.GetNorthEastCorner()?.GetLatitude()?.toDouble()
                                                        ?: 0.0, radarInfo.first.value.GetSouthWestCorner()?.GetLongitude()?.toDouble()
                                                        ?: 0.0),
                                                        LatLng(radarInfo.first.value.GetNorthEastCorner()?.GetLatitude()?.toDouble()
                                                                ?: 0.0, radarInfo.first.value.GetNorthEastCorner()?.GetLongitude()?.toDouble()
                                                                ?: 0.0),
                                                        LatLng(radarInfo.first.value.GetSouthWestCorner()?.GetLatitude()?.toDouble()
                                                                ?: 0.0, radarInfo.first.value.GetNorthEastCorner()?.GetLongitude()?.toDouble()
                                                                ?: 0.0),
                                                        LatLng(radarInfo.first.value.GetSouthWestCorner()?.GetLatitude()?.toDouble()
                                                                ?: 0.0, radarInfo.first.value.GetSouthWestCorner()?.GetLongitude()?.toDouble()
                                                                ?: 0.0)))
                                                mMapController.SetCameraPosition(radarInfo.first.value.GetLatitude()?.toDouble()
                                                        ?: 0.0, radarInfo.first.value.GetLongitude()?.toDouble()
                                                        ?: 0.0, 5.0)
                                            }
                                        }
                                    }
                                }
                            })

                            mRadarImageDataProvider.GetRadarAnimationObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<List<RadarDataController.ImageInfo>> {
                                override fun onComplete() {}
                                override fun onSubscribe(d: Disposable) {
                                    mImageListDisposable?.dispose()
                                    mImageListDisposable = d
                                }

                                override fun onNext(inImageList: List<RadarDataController.ImageInfo>) {
                                    mHoldPlay = false
                                    mMapController.SetFrameCount(inImageList.size)

                                    (mBinding?.playPauseButton as View?)?.visibility = View.VISIBLE
                                }

                                override fun onError(e: Throwable) {
                                    e.printStackTrace()
                                    SetSnackbarText("Error", "", "")
                                }
                            })



                            Observables.combineLatest(mMapController.GetActiveImageIndexObservable(),
                                    mRadarImageDataProvider.GetSiteInfoObservable(),
                                    mRadarImageDataProvider.GetRadarAnimationObservable())
                            { activeImage, radarInfo, imageList ->

                                Pair(imageList[activeImage], radarInfo)
                            }.observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Pair<RadarDataController.ImageInfo, Map.Entry<String, RadarDetails>>> {
                                override fun onComplete() {}
                                override fun onSubscribe(d: Disposable) {
                                    mActiveImageDisposable?.dispose()
                                    mActiveImageDisposable = d
                                }

                                override fun onNext(pair: Pair<RadarDataController.ImageInfo, Map.Entry<String, RadarDetails>>) {
                                    if (!pair.first.GetImage().isRecycled) {
                                        if (!mHoldPlay)
                                            mRadarImageSource?.setImage(pair.first.GetImage())
                                        if(mState == PageStateInfo.PageState.kData)
                                            SetSnackbarText(pair.second.key, pair.second.value.GetName(), if (mHoldPlay) "" else pair.first.GetTimestring())
                                    }
                                }

                                override fun onError(e: Throwable) {
                                    SetSnackbarText("Error", "", "")
                                    e.printStackTrace()
                                }
                            })
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            mBinding?.overlayOpacityText?.visibility = View.VISIBLE
    }


    override fun onPause() {
        mRadarImageDataProvider.OnPause()

        mPageStateDisposable?.dispose()

        mActiveImageDisposable?.dispose()
        mImageListDisposable?.dispose()

        mPageStateDisposable = null
        mActiveImageDisposable = null
        mImageListDisposable = null

        super.onPause()
    }

    override fun onDestroyView() {
        mBinding?.playPauseButton?.Dispose()
        super.onDestroyView()
    }


    override fun onDestroy() {
        mMapController.Dispose()

        mTransparencyDisposable?.dispose()
        mSelectedRadarDisposable?.dispose()
        mCameraUpdateDisposable?.dispose()

        mTransparencyDisposable = null
        mSelectedRadarDisposable = null
        mCameraUpdateDisposable = null

        mEmtpyImage.recycle()

        super.onDestroy()
    }


    override fun Close() {
        Observable.create(ObservableOnSubscribe<Void>{
            RevealView(mBinding?.radarLayout, mBinding?.childFragmentContainer)
            mBinding?.readingInfoLayout?.visibility = View.VISIBLE
            (mBinding?.playPauseButton as View?)?.visibility = View.VISIBLE
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


    private fun RevealView(inClickView: View?, inViewToReveal: View?) {
        val cx = inClickView?.left ?: 0 + (inClickView?.right ?: 0) - 24
        val cy = (inClickView?.top ?: 0 + (inClickView?.bottom ?: 0)) - 24
        val radius = Math.max(inViewToReveal?.width ?: 0, inViewToReveal?.height ?: 0) * 2.0f

        if (inViewToReveal?.visibility != View.VISIBLE) {
            inViewToReveal?.visibility = View.VISIBLE
            val hide = ViewAnimationUtils.createCircularReveal(inViewToReveal, cx, cy, 0f, radius)
            hide.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    inViewToReveal?.visibility = View.VISIBLE
                    hide.removeListener(this)
                }
            })
            hide.start()

        } else {
            val reveal = ViewAnimationUtils.createCircularReveal(
                    inViewToReveal, cx, cy, radius, 0f)
            reveal.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    inViewToReveal.visibility = View.INVISIBLE
                    reveal.removeListener(this)
                }
            })
            reveal.start()
        }
    }



    internal fun SetSnackbarText(inRadarId: String?, inRadarName: String?, inTimeString: String?)
    {
        if(isAdded && !isDetached) {
            var timeString = ""

            if (inTimeString != null) {
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                    timeString = "<br>"
                else
                    timeString += "&nbsp;&nbsp;&nbsp; "
            }

            timeString += inTimeString ?: ""

            val radarId = inRadarId ?: ""
            val radarName = inRadarName ?: ""

            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                mBinding?.readingInfoTextView?.text = Html.fromHtml("<b><span style=\"color: #acc0fb;\">$radarId</span>&nbsp;&nbsp; $radarName</b>$timeString\n", 0)
            else
                mBinding?.readingInfoTextView?.text = Html.fromHtml("<b><font color=\"#acc0fb\">$radarId</font>&nbsp;&nbsp; $radarName</b>$timeString\n")
        }
    }
}
