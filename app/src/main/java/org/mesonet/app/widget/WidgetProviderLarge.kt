package org.mesonet.app.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.squareup.picasso.Picasso
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.mesonet.app.R
import org.mesonet.dataprocessing.site.forecast.FiveDayForecastDataController
import org.mesonet.dataprocessing.site.forecast.ForecastData
import org.mesonet.dataprocessing.site.forecast.SemiDayForecastDataController
import javax.inject.Inject


class WidgetProviderLarge @Inject constructor() : WidgetProvider() {

    @Inject
    lateinit var mFiveDayForecastDataController: FiveDayForecastDataController

    private var mForecast1DownloadDone = false
    private var mForecast2DownloadDone = false


    override fun GetLayoutId(): Int {
        return R.layout.widget_large
    }


    internal fun SetForecastViews(inContext: Context, inAppWidgetManager: AppWidgetManager, inRemoteViews: RemoteViews, inAppWidgetIds: IntArray) {
        if (mFiveDayForecastDataController.GetCount() > 1) {
            mFiveDayForecastDataController.GetForecast(0).GetForecastDataObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<ForecastData>{
                var disposable: Disposable? = null
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable)
                {
                    disposable = d
                }
                override fun onNext(t: ForecastData) {
                    val iconUrl1 = t.GetIconUrl()
                    val highOrLow = t.GetHighOrLowTemp().split(" ")
                    inRemoteViews.setTextViewText(R.id.widget_forecast_condition1, t.GetStatus())
                    inRemoteViews.setTextViewText(R.id.widget_forecast_loworhightemp1, highOrLow.first())
                    inRemoteViews.setTextViewText(R.id.widget_forecast_loworhigh1, highOrLow.last())
                    inRemoteViews.setTextViewText(R.id.widget_forecast_time1, t.GetTime())
                    if (!iconUrl1.isEmpty())
                        Picasso.get().load(iconUrl1).into(inRemoteViews, R.id.widget_forecast_image1, inAppWidgetIds)

                    UpdateWidgets(inContext, inAppWidgetManager, inRemoteViews, inAppWidgetIds)

                    disposable?.dispose()
                    disposable = null

                    mForecast1DownloadDone = true
                    CheckIfDoneWithObservables()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    onNext(object: ForecastData{
                        override fun GetTime(): String {
                            return ""
                        }

                        override fun GetIconUrl(): String {
                            return ""
                        }

                        override fun GetStatus(): String {
                            return ""
                        }

                        override fun GetHighOrLowTemp(): String {
                            return ""
                        }

                        override fun GetWindDescription(): String {
                            return ""
                        }

                        override fun compareTo(other: ForecastData): Int {
                            return 1
                        }

                    })
                }

            })

            mFiveDayForecastDataController.GetForecast(1).GetForecastDataObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<ForecastData>{
                var disposable: Disposable? = null
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable)
                {
                    disposable = d
                }
                override fun onNext(t: ForecastData) {
                    val iconUrl2 = t.GetIconUrl()
                    val highOrLow = t.GetHighOrLowTemp().split(" ")
                    inRemoteViews.setTextViewText(R.id.widget_forecast_condition2, t.GetStatus())
                    inRemoteViews.setTextViewText(R.id.widget_forecast_loworhightemp2, highOrLow.first())
                    inRemoteViews.setTextViewText(R.id.widget_forecast_loworhigh2, highOrLow.last())
                    inRemoteViews.setTextViewText(R.id.widget_forecast_time2, t.GetTime())
                    if (!iconUrl2.isEmpty())
                        Picasso.get().load(iconUrl2).into(inRemoteViews, R.id.widget_forecast_image2, inAppWidgetIds)

                    UpdateWidgets(inContext, inAppWidgetManager, inRemoteViews, inAppWidgetIds)

                    disposable?.dispose()
                    disposable = null

                    mForecast2DownloadDone = true
                    CheckIfDoneWithObservables()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    onNext(object: ForecastData{
                        override fun GetTime(): String {
                            return ""
                        }

                        override fun GetIconUrl(): String {
                            return ""
                        }

                        override fun GetStatus(): String {
                            return ""
                        }

                        override fun GetHighOrLowTemp(): String {
                            return ""
                        }

                        override fun GetWindDescription(): String {
                            return ""
                        }

                        override fun compareTo(other: ForecastData): Int {
                            return 1
                        }
                    })
                }
            })
        }
    }


    override fun CheckIfDoneWithObservables()
    {
        if(mForecast1DownloadDone && mForecast2DownloadDone) {
            super.CheckIfDoneWithObservables()
            mFiveDayForecastDataController.OnPause()
            mFiveDayForecastDataController.OnDestroy()
        }
    }


    override fun Update(inContext: Context, inAppWidgetManager: AppWidgetManager, inRemoteViews: RemoteViews, inAppWidgetIds: IntArray) {
        mForecast1DownloadDone = false
        mForecast2DownloadDone = false
        super.Update(inContext, inAppWidgetManager, inRemoteViews, inAppWidgetIds)

        mFiveDayForecastDataController.OnCreate(inContext)
        mFiveDayForecastDataController.OnResume(inContext)

        mFiveDayForecastDataController.GetForecastDataSubject(inContext).observeOn(AndroidSchedulers.mainThread()).subscribe (object: Observer<List<SemiDayForecastDataController>>
        {
            var disposable: Disposable? = null

            override fun onComplete() {}
            override fun onSubscribe(d: Disposable)
            {
                disposable = d
            }

            override fun onNext(t: List<SemiDayForecastDataController>) {
                disposable?.dispose()
                disposable = null
                SetForecastViews(inContext, inAppWidgetManager, inRemoteViews, inAppWidgetIds)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                onNext(ArrayList())
            }
        })
    }


    override fun GetClickLayoutId(): Int {
        return R.id.widget_large_layout
    }


    override fun GetWidgetClickIntent(inContext: Context): Intent {
        return Intent(inContext, WidgetProviderLarge::class.java)
    }


    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        mFiveDayForecastDataController.OnDestroy()
        mMesonetUIController.OnDestroy()
        super.onDeleted(context, appWidgetIds)
    }
}