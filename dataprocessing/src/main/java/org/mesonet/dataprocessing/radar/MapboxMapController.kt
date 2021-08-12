package org.mesonet.dataprocessing.radar

import com.mapbox.mapboxsdk.camera.CameraPosition
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

interface MapboxMapController
{
    fun GetPlayPauseStateObservable(): Observable<Boolean>
    fun TogglePlay()
    fun GetTransparencySubject(): BehaviorSubject<Float>
    fun SetCameraPosition(inLat: Double, inLon: Double, inZoom: Double)
    fun GetCameraPositionObservable(): Observable<CameraPosition>
    fun SetFrameCount(inFrameCount: Int)
    fun GetActiveImageIndexObservable(): Observable<Int>
    fun Dispose()
}