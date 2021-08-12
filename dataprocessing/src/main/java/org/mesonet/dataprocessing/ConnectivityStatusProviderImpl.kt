package org.mesonet.dataprocessing

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.core.content.ContextCompat
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.core.PerContext
import java.security.Permissions
import javax.inject.Inject


@PerContext
class ConnectivityStatusProviderImpl @Inject constructor(): ConnectivityManager.NetworkCallback(), ConnectivityStatusProvider {
    private val mConnectivityStatusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
    var mNetworkRequest: NetworkRequest = NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()

    private var mConnectivityManager: ConnectivityManager? = null

    private var mPaused = true

    override fun OnCreate(inContext: Context) {
        mConnectivityManager = inContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun OnResume(inContext: Context) {
        synchronized(this)
        {
           /* mConnectivityStatusSubject.onNext(mConnectivityManager?.activeNetwork != null && mConnectivityManager?.activeNetworkInfo?.isConnected == true)
            */
            val isConnected = (mConnectivityManager?.getNetworkCapabilities(mConnectivityManager?.activeNetwork))?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    ?: false
            mConnectivityStatusSubject.onNext(mConnectivityManager?.activeNetwork != null && isConnected)
                mConnectivityManager?.registerNetworkCallback(mNetworkRequest, this)

            mPaused = false
        }
    }

    override fun OnPause() {
        synchronized(this)
        {
            if(!mPaused)
            {
                mConnectivityManager?.unregisterNetworkCallback(this)
                mPaused = true
            }
        }
    }

    override fun OnDestroy()
    {
        mConnectivityStatusSubject.onComplete()
    }


    override fun ConnectivityStatusObservable(): Observable<Boolean> {
        return mConnectivityStatusSubject.distinctUntilChanged{ it -> it }
    }



    override fun onAvailable(inNetwork: Network) {
        super.onAvailable(inNetwork)

        mConnectivityStatusSubject.onNext(true)
    }


    override fun onUnavailable() {
        super.onUnavailable()

        mConnectivityStatusSubject.onNext(false)
    }

    override fun onLost(network: Network) {
            super.onLost(network)

        mConnectivityStatusSubject.onNext(false)
    }
}