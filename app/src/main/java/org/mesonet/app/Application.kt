package org.mesonet.app

import android.app.Activity

import org.mesonet.app.dependencyinjection.DaggerApplicationComponent

import javax.inject.Inject
import javax.inject.Singleton

import android.content.BroadcastReceiver
import android.content.Context
import com.mapbox.android.telemetry.MapboxTelemetry
import com.mapbox.android.telemetry.TelemetryEnabler
import com.mapbox.android.telemetry.TelemetryUtils
import com.mapbox.mapboxsdk.Mapbox
import dagger.android.*
import org.mesonet.app.dependencyinjection.ApplicationContextModule
import java.util.*


@Singleton
class Application @Inject
constructor() : android.app.Application(), HasActivityInjector, HasBroadcastReceiverInjector {
    @Inject
    internal lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var mBroadcastReceiverInjector: DispatchingAndroidInjector<BroadcastReceiver>


    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().build().Inject(this)

        val sharedPreferences = getSharedPreferences("MapboxSharedPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("mapboxTelemetryState",
                TelemetryEnabler.State.DISABLED.name).apply()

        Mapbox.getInstance(applicationContext, getString(R.string.mapbox_access_token))

    }


    override fun activityInjector(): AndroidInjector<Activity>? {
        return mActivityInjector
    }


    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> {
        return mBroadcastReceiverInjector
    }
}