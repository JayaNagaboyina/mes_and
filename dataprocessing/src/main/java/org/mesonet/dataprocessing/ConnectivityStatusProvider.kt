package org.mesonet.dataprocessing

import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import org.mesonet.dataprocessing.LifecycleListener

interface ConnectivityStatusProvider: LifecycleListener
{
    fun ConnectivityStatusObservable(): Observable<Boolean>
}