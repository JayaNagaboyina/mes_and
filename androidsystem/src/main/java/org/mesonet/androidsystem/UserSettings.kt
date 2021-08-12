package org.mesonet.androidsystem

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.core.PerContext
import org.mesonet.dataprocessing.userdata.Preferences

import javax.inject.Inject


@PerContext
class UserSettings @Inject constructor() : Preferences {
    val mUnitPreferenceSubject: BehaviorSubject<Preferences.UnitPreference> = BehaviorSubject.create()
    val mMapsDisplayModeSubject: BehaviorSubject<Preferences.MapsDisplayModePreference> = BehaviorSubject.create()
    val mRadarColorThemeSubject: BehaviorSubject<Preferences.RadarColorThemePreference> = BehaviorSubject.create()

    val mStidSubject: BehaviorSubject<String> = BehaviorSubject.create()
    val mRadarSubject: BehaviorSubject<String> = BehaviorSubject.create()

    internal fun SetPreference(inContext: Context, inName: String, inValue: String): Single<Int> {
        return Single.create {
            synchronized(this)
            {
                val preferences = inContext.getSharedPreferences(kMesonetSettings, Context.MODE_PRIVATE)

                val editor = preferences.edit()
                editor.putString(inName, inValue)
                editor.apply()

                it.onSuccess(0)
            }
        }
    }


    internal fun GetStringPreference(inContext: Context, inName: String, inDefault: String): String {
        synchronized(this)
        {
            val preferences = inContext.getSharedPreferences(kMesonetSettings, Context.MODE_PRIVATE)

            return preferences.getString(inName, inDefault)?: ""
        }
    }


    override fun UnitPreferencesObservable(inContext: Context): Observable<Preferences.UnitPreference> {
        val preference = Preferences.UnitPreference.valueOf(GetStringPreference(inContext, kUnitPreference, Preferences.UnitPreference.kImperial.name))
        if(!mUnitPreferenceSubject.hasValue() || mUnitPreferenceSubject.value != preference)
            mUnitPreferenceSubject.onNext(preference)

        return mUnitPreferenceSubject
    }


    override fun MapsDisplayModePreferenceObservable(inContext: Context): Observable<Preferences.MapsDisplayModePreference> {
        val preference = Preferences.MapsDisplayModePreference.valueOf(GetStringPreference(inContext, kMapsDisplayModePreference, Preferences.MapsDisplayModePreference.kTraditional.name))
        if(!mMapsDisplayModeSubject.hasValue() || mMapsDisplayModeSubject.value != preference)
            mMapsDisplayModeSubject.onNext(preference)

        return mMapsDisplayModeSubject
    }


    override fun RadarColorThemePreferenceObservable(inContext: Context): Observable<Preferences.RadarColorThemePreference> {
        val preference = Preferences.RadarColorThemePreference.valueOf(GetStringPreference(inContext, kRadarColorThemePreference, Preferences.RadarColorThemePreference.kLight.name))
        if(!mRadarColorThemeSubject.hasValue() || mRadarColorThemeSubject.value != preference)
            mRadarColorThemeSubject.onNext(preference)

        return mRadarColorThemeSubject
    }


    override fun SetUnitPreference(inContext: Context, inPreference: Preferences.UnitPreference): Single<Int> {
        return SetPreference(inContext, kUnitPreference, inPreference.toString()).doOnSubscribe{
            mUnitPreferenceSubject.onNext(inPreference)
        }.subscribeOn(Schedulers.computation())
    }


    override fun SetMapsDisplayModePreference(inContext: Context, inPreference: Preferences.MapsDisplayModePreference): Single<Int> {
        return SetPreference(inContext, kMapsDisplayModePreference, inPreference.toString()).doOnSubscribe{
            mMapsDisplayModeSubject.onNext(inPreference)
        }.subscribeOn(Schedulers.computation())
    }


    override fun SetRadarColorThemePreference(inContext: Context, inPreference: Preferences.RadarColorThemePreference): Single<Int> {
        return SetPreference(inContext, kRadarColorThemePreference, inPreference.toString()).doOnSubscribe {
            mRadarColorThemeSubject.onNext(inPreference)
        }.subscribeOn(Schedulers.computation())
    }


    override fun SelectedStidObservable(inContext: Context): Observable<String> {
        val preference = GetStringPreference(inContext, kSelectedStid, "")
        if(!mStidSubject.hasValue() || mStidSubject.value != preference)
            mStidSubject.onNext(preference)

        return mStidSubject
    }

    override fun SetSelectedStid(inContext: Context, inStid: String): Single<Int> {
        return SetPreference(inContext, kSelectedStid, inStid).doOnSubscribe {
            mStidSubject.onNext(inStid)
        }.subscribeOn(Schedulers.computation())
    }

    override fun SelectedRadarObservable(inContext: Context): Observable<String> {
        val preference = GetStringPreference(inContext, kSelectedRadar, "")
        if(!mRadarSubject.hasValue() || mRadarSubject.value != preference)
            mRadarSubject.onNext(preference)

        return mRadarSubject
    }

    override fun SetSelectedRadar(inContext: Context, inRadarName: String): Single<Int> {
        return SetPreference(inContext, kSelectedRadar, inRadarName).doOnSubscribe{
            mRadarSubject.onNext(inRadarName)
        }.subscribeOn(Schedulers.computation())
    }

    override fun Dispose() {
        mUnitPreferenceSubject.onComplete()
        mStidSubject.onComplete()
        mRadarSubject.onComplete()
    }


    companion object {
        private const val kMesonetSettings = "MesonetSettings"
        const val kUnitPreference = "UnitPreference"
        const val kMapsDisplayModePreference = "MapsDisplayModePreference"
        const val kRadarColorThemePreference = "RadarColorThemePreference"
        const val kSelectedStid = "SelectedStid"
        const val kSelectedRadar = "SelectedRadar"
    }
}