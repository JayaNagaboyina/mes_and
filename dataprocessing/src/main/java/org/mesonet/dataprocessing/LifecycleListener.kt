package org.mesonet.dataprocessing

import android.content.Context

interface LifecycleListener
{
    fun OnCreate(inContext: Context)
    fun OnResume(inContext: Context)
    fun OnPause()
    fun OnDestroy()
}