package org.mesonet.app.site.forecast

import android.app.Activity
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.mesonet.app.R

import org.mesonet.app.databinding.ForecastLayoutBinding
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.dataprocessing.site.forecast.ForecastData
import org.mesonet.dataprocessing.site.forecast.ForecastLayoutController
import org.mesonet.dataprocessing.site.forecast.SemiDayForecastDataController


class ForecastLayout(inActivity: Activity, val inIndex: Int) : RelativeLayout(inActivity), Observer<ForecastLayoutController.ForecastDisplayData>
{
    internal val mBinding: ForecastLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.forecast_layout, this, true)
    private val mActivity = inActivity
    private var mForecastImageUrl: String? = null

    private var mDisposable: Disposable? = null
    private var mDataDisposable: Disposable? = null
    private var mPageStateDisposable: Disposable? = null



    internal fun SetData(inForecastData: ForecastData)
    {
        mDisposable?.dispose()
        ForecastLayoutController(context, inForecastData).GetForecastObservable().observeOn(AndroidSchedulers.mainThread())?.subscribe(this)
    }


    fun SetController(inController: SemiDayForecastDataController)
    {
        inController.GetPageStateObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<PageStateInfo>{
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {
                mPageStateDisposable = d
            }

            override fun onNext(t: PageStateInfo) {
                when(t.GetPageState())
                {
                    PageStateInfo.PageState.kData -> {
                        mBinding.errorText.visibility = View.GONE
                        mBinding.forecastProgressBar.visibility = View.GONE
                        mBinding.dataLayout.visibility = View.VISIBLE
                    }
                    PageStateInfo.PageState.kLoading -> {
                        mBinding.errorText.visibility = View.GONE
                        mBinding.dataLayout.visibility = View.GONE
                        mBinding.forecastProgressBar.visibility = View.VISIBLE
                    }
                    PageStateInfo.PageState.kError -> {
                        mBinding.forecastProgressBar.visibility = View.GONE
                        mBinding.dataLayout.visibility = View.GONE
                        mBinding.errorText.text = t.GetErrorMessage()
                        mBinding.errorText.visibility = View.GONE
                    }
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })

        inController.GetForecastDataObservable().observeOn(Schedulers.computation()).subscribe(object: Observer<ForecastData>{
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {
                mDataDisposable = d
            }

            override fun onNext(t: ForecastData) {
                SetData(t)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }


    override fun onComplete() {}
    override fun onSubscribe(d: Disposable)
    {
        mDisposable = d
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }


    override fun onNext(t: ForecastLayoutController.ForecastDisplayData) {
        mBinding.forecastDisplayData = t

        mBinding.invalidateAll()

        val imageUrl = t.GetImageUrl()

        if (!imageUrl.isEmpty() && imageUrl != mForecastImageUrl) {
            mForecastImageUrl = imageUrl
            Picasso.get().load(imageUrl).into(mBinding.image)
        }
    }

    fun Dispose()
    {
        mDisposable?.dispose()
        mDisposable = null
        mPageStateDisposable?.dispose()
        mPageStateDisposable = null
        mDataDisposable?.dispose()
        mDataDisposable = null
    }
}