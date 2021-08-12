package org.mesonet.dataprocessing

import android.content.Context
import android.location.Location
import io.reactivex.Observable
import io.reactivex.Single

interface LocationProvider
{
    fun GetLocation(): Single<LocationResult>
    fun RegisterGpsResult(inResultCode: Int)


    interface LocationResult
    {
        fun LocationResult(): Location?
    }
    /*fun getDevicePermissions()*/
}