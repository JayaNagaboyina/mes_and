package org.mesonet.androidsystem

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat

import io.reactivex.schedulers.Schedulers

import org.mesonet.dataprocessing.LocationProvider
import android.content.Context
import android.content.IntentSender
import android.os.Looper
import android.util.Log
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import io.reactivex.*
import io.reactivex.disposables.Disposable
import org.mesonet.core.PerContext

import javax.inject.Inject


@PerContext
class DeviceLocation @Inject
constructor(var mContext: Context) : LocationProvider
{
    @Inject
    internal lateinit var mPermissions: Permissions

    val mLocationProviderClient = LocationServices.getFusedLocationProviderClient(mContext)

    val mWaitingObservers: ArrayList<SingleEmitter<LocationProvider.LocationResult>> = ArrayList()

    override fun GetLocation(): Single<LocationProvider.LocationResult> {
        return Single.create (SingleOnSubscribe<LocationProvider.LocationResult>{ locationSubscriber ->
            mPermissions.RequestPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION).observeOn(Schedulers.computation()).subscribe(object: SingleObserver<Boolean> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(t: Boolean) {
                    if (t && ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (Looper.myLooper() == null)
                            Looper.prepare()
                        val locationRequest = LocationRequest().apply {
                            interval = 1000
                            fastestInterval = 1000
                            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        }

                        val builder = LocationSettingsRequest.Builder()
                                .addLocationRequest(locationRequest)

                        val client: SettingsClient = LocationServices.getSettingsClient(mContext)
                        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

                        task.addOnSuccessListener {
                            mLocationProviderClient.requestLocationUpdates(locationRequest,
                                    object : LocationCallback() {
                                        override


                                        fun onLocationResult(locationResult: LocationResult?) {
                                            if (locationResult == null) {
                                                locationSubscriber.onSuccess(LocationResultImpl(null))
                                                return
                                            }

                                            locationSubscriber.onSuccess(LocationResultImpl(locationResult.locations.first()))
                                            mLocationProviderClient.removeLocationUpdates(this)
                                        }
                                    },
                                    null)
                        }

                        task.addOnFailureListener { exception ->
                            if (exception is ResolvableApiException) {
                                if (mContext is Activity) {
                                    try {
                                        mWaitingObservers.add(locationSubscriber)
                                        exception.startResolutionForResult(mContext as Activity, mPermissions.LocationRequestCode())
                                    } catch (sendEx: IntentSender.SendIntentException) {
                                        locationSubscriber.onSuccess(LocationResultImpl(null))
                                    }
                                } else {
                                    locationSubscriber.onSuccess(LocationResultImpl(null))
                                }
                            }
                            else {
                                locationSubscriber.onSuccess(LocationResultImpl(null))
                            }
                        }

                    } else {
                        locationSubscriber.onSuccess(LocationResultImpl(null))
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    onSuccess(false)
                }
            })

        }).subscribeOn(Schedulers.computation())
    }


    override fun RegisterGpsResult(inResultCode: Int) {
        synchronized(mWaitingObservers)
        {
            while (mWaitingObservers.size > 0) {
                if (inResultCode == Activity.RESULT_OK) {
                    val locationRequest = LocationRequest().apply {
                        interval = 1000
                        fastestInterval = 1000
                        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                    }

                    val locationSubscriber = mWaitingObservers[0]

                    if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mLocationProviderClient.requestLocationUpdates(locationRequest,
                                object : LocationCallback() {
                                    override


                                    fun onLocationResult(locationResult: LocationResult?) {
                                        if (locationResult == null) {
                                            locationSubscriber.onSuccess(LocationResultImpl(null))
                                            return
                                        }

                                        locationSubscriber.onSuccess(LocationResultImpl(locationResult.locations.first()))
                                        mLocationProviderClient.removeLocationUpdates(this)
                                    }
                                },
                                null)
                    }
                } else {
                    mWaitingObservers[0].onSuccess(LocationResultImpl(null))
                }

                mWaitingObservers.removeAt(0)
            }
        }
    }

    /*override fun getDevicePermissions() {
        if(!(ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            Log.d("DeviceLocation:", "Permissions for read_phone_state is not granted")
            mPermissions.RequestPermission(mContext, Manifest.permission.READ_PHONE_STATE).observeOn(Schedulers.computation()).subscribe(object: SingleObserver<Boolean> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(t: Boolean) {
                    if (t && ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                        Log.d("DeviceLocation: ", "Phone_state permissions granted")
                    }

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
            //mPermissions.RequestPermission(mContext, Manifest.permission.READ_PHONE_STATE)
        }
    }*/


    class LocationResultImpl(val mLocation: Location?): LocationProvider.LocationResult
    {
        override fun LocationResult(): Location? {
            return mLocation
        }
    }

}