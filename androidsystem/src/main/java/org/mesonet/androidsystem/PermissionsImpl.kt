package org.mesonet.androidsystem

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class PermissionsImpl @Inject
constructor(): Permissions {
    override fun LocationRequestCode(): Int {
        return 0
    }

    private val mPermissionsObservers = HashMap<String, MutableList<SingleEmitter<Boolean>>>()


    override fun RequestPermission(inContext: Context?, inPermission: String): Single<Boolean> {
        return Single.create(SingleOnSubscribe<Boolean> {
            synchronized(Permissions@this)
            {
                if (!mPermissionsObservers.containsKey(inPermission))
                    mPermissionsObservers[inPermission] = ArrayList()

                if (mPermissionsObservers[inPermission]?.contains(it) == false)
                    mPermissionsObservers[inPermission]?.add(it)

                if(inContext == null)
                {
                    it.onSuccess(false)
                    return@SingleOnSubscribe
                }
                if (ContextCompat.checkSelfPermission(inContext, inPermission) != PackageManager.PERMISSION_GRANTED) {
                    if (inContext is Activity) {
                        ActivityCompat.requestPermissions(inContext,
                                arrayOf(inPermission),
                                0)
                    } else {
                        it.onSuccess(false)
                    }
                } else {
                    it.onSuccess(true)
                }
            }
        }).subscribeOn(Schedulers.computation())
    }


    override fun ProcessPermissionResponse(inPermissions: Array<String>, inGrantResults: IntArray) {
        Single.create(SingleOnSubscribe<Int> {
            for (i in inPermissions.indices) {
                if (mPermissionsObservers.containsKey(inPermissions[i])) {
                    val permissionListeners = mPermissionsObservers[inPermissions[i]]

                    val j = 0
                    if (permissionListeners != null) {
                        while (j < permissionListeners.size) {
                            permissionListeners[j].onSuccess(inGrantResults[i] == PackageManager.PERMISSION_GRANTED)
                            permissionListeners.removeAt(j)
                        }
                    }
                }
            }

            it.onSuccess(0)
        }).subscribeOn(Schedulers.computation()).subscribe(object: SingleObserver<Int>{
            override fun onSuccess(t: Int) {}
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }
}