package org.mesonet.dataprocessing.userdata

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


interface Preferences {
    enum class UnitPreference {
        kMetric, kImperial
    }

    enum class MapsDisplayModePreference {
        kTraditional, kThumbnail
    }

    enum class RadarColorThemePreference {
        kLight, kDark
    }


    fun UnitPreferencesObservable(inContext: Context): Observable<UnitPreference>
    fun MapsDisplayModePreferenceObservable(inContext: Context): Observable<MapsDisplayModePreference>
    fun RadarColorThemePreferenceObservable(inContext: Context): Observable<RadarColorThemePreference>
    fun SetUnitPreference(inContext: Context, inPreference: UnitPreference): Single<Int>
    fun SetMapsDisplayModePreference(inContext: Context, inPreference: MapsDisplayModePreference): Single<Int>
    fun SetRadarColorThemePreference(inContext: Context, inPreference: RadarColorThemePreference): Single<Int>
    fun SelectedStidObservable(inContext: Context): Observable<String>
    fun SetSelectedStid(inContext: Context, inStid: String): Single<Int>
    fun SelectedRadarObservable(inContext: Context): Observable<String>
    fun SetSelectedRadar(inContext: Context, inRadarName: String): Single<Int>
    fun Dispose()
}