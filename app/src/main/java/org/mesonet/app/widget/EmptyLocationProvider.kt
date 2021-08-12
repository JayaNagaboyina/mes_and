package org.mesonet.app.widget

import android.content.Context
import android.location.Location
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import org.mesonet.dataprocessing.LocationProvider
import javax.inject.Inject



class EmptyLocationProvider @Inject constructor() : LocationProvider {
    override fun RegisterGpsResult(inResultCode: Int) {
    }

    override fun GetLocation(): Single<LocationProvider.LocationResult> {
        return Single.create {
            it.onSuccess(object: LocationProvider.LocationResult{
                override fun LocationResult(): Location? {
                    return null
                }

            })

        }
    }

    /*override fun getDevicePermissions() {
    }*/

}