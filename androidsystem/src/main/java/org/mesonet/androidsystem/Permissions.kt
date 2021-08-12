package org.mesonet.androidsystem

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single

interface Permissions
{
    fun LocationRequestCode(): Int
    fun RequestPermission(inContext: Context?, inPermission: String): Single<Boolean>
    fun ProcessPermissionResponse(inPermissions: Array<String>, inGrantResults: IntArray)
}