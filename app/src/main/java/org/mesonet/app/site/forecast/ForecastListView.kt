package org.mesonet.app.site.forecast

import android.app.Activity
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import io.reactivex.disposables.Disposable
import org.mesonet.app.R
import org.mesonet.app.databinding.ForecastListViewBinding
import org.mesonet.dataprocessing.site.forecast.SemiDayForecastDataController

class ForecastListView(inActivity: Activity) : LinearLayout(inActivity) {
    private var mBinding: ForecastListViewBinding? = null

    private var mDisposable: Disposable? = null

    private var mActivity: Activity = inActivity

    init {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(inActivity), R.layout.forecast_list_view, this, true)
    }


    internal fun SetSemiDayForecast(inForecastIndex: Int, inController: SemiDayForecastDataController) {
        if (mBinding != null) {
            val totalForecastsCount = resources.getInteger(R.integer.forecastsPerPage)

            while (mBinding?.layout?.childCount?: Int.MAX_VALUE <= inForecastIndex % totalForecastsCount) {

                val forecastLayout = ForecastLayout(mActivity, inForecastIndex)
                mBinding?.layout?.addView(forecastLayout)
                forecastLayout.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f)
            }

            val child = mBinding?.layout?.getChildAt(inForecastIndex % totalForecastsCount)

            (child as? ForecastLayout)?.SetController(inController)
        }
    }


    fun Dispose() {
        mDisposable?.dispose()
        mDisposable = null
        for (i in 0..(mBinding?.layout?.childCount ?: 0-1)) {
            if (mBinding?.layout?.getChildAt(i) is ForecastLayout)
                (mBinding?.layout?.getChildAt(i) as ForecastLayout).Dispose()
        }
    }
}