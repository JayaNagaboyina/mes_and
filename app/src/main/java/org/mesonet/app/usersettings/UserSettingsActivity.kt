package org.mesonet.app.usersettings

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.mesonet.app.baseclasses.BaseActivity
import org.mesonet.app.databinding.UserSettingsActivityBinding
import org.mesonet.dataprocessing.userdata.Preferences
import javax.inject.Inject
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import org.mesonet.app.R


class UserSettingsActivity: BaseActivity()
{
    @Inject
    lateinit var mPreferences: Preferences

    lateinit var mBinding: UserSettingsActivityBinding

    private var mGetUnitsPreferenceDisposable: Disposable? = null
    private var mGetMapsDisplayModePreferenceDisposable: Disposable? = null
    private var mGetRadarColorThemePreferenceDisposable: Disposable? = null
    private var mSetUnitsPreferenceDisposable: Disposable? = null
    private var mSetMapsDisplayModePreferenceDisposable: Disposable? = null
    private var mSetRadarColorThemePreferenceDisposable: Disposable? = null

    public override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.user_settings_activity)

        mPreferences.UnitPreferencesObservable(this).observeOn(AndroidSchedulers.mainThread()).subscribe (object: Observer<Preferences.UnitPreference>
        {
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable)
            {
                mGetUnitsPreferenceDisposable?.dispose()
                mGetUnitsPreferenceDisposable = d
            }
            override fun onNext(t: Preferences.UnitPreference) {
                when (t) {
                    Preferences.UnitPreference.kImperial -> mBinding.imperialButton.isChecked = true
                    Preferences.UnitPreference.kMetric -> mBinding.metricButton.isChecked = true
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                onNext(Preferences.UnitPreference.kImperial)
            }

        })

        mPreferences.MapsDisplayModePreferenceObservable(this).observeOn(AndroidSchedulers.mainThread()).subscribe (object: Observer<Preferences.MapsDisplayModePreference>
        {
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable)
            {
                mGetMapsDisplayModePreferenceDisposable?.dispose()
                mGetMapsDisplayModePreferenceDisposable = d
            }
            override fun onNext(t: Preferences.MapsDisplayModePreference) {
                when (t) {
                    Preferences.MapsDisplayModePreference.kTraditional -> mBinding.traditionalMapsButton.isChecked = true
                    Preferences.MapsDisplayModePreference.kThumbnail -> mBinding.thumbnailMapsButton.isChecked = true
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                onNext(Preferences.MapsDisplayModePreference.kTraditional)
            }

        })


        mPreferences.RadarColorThemePreferenceObservable(this).observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<Preferences.RadarColorThemePreference>{
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {
                mGetRadarColorThemePreferenceDisposable?.dispose()
                mGetRadarColorThemePreferenceDisposable = d
            }

            override fun onNext(t: Preferences.RadarColorThemePreference) {
                when (t) {
                    Preferences.RadarColorThemePreference.kLight -> mBinding.lightThemeButton.isChecked = true
                    Preferences.RadarColorThemePreference.kDark -> mBinding.darkThemeButton.isChecked = true
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                onNext(Preferences.RadarColorThemePreference.kLight)
            }

        })

        mBinding.unitsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val preference= when(checkedId) {
                R.id.imperialButton -> Preferences.UnitPreference.kImperial
                R.id.metricButton -> Preferences.UnitPreference.kMetric
                else -> Preferences.UnitPreference.kImperial
            }

            mPreferences.SetUnitPreference(this, preference).subscribe(object: SingleObserver<Int>{
                override fun onSuccess(t: Int) {}
                override fun onSubscribe(d: Disposable)
                {
                    mSetUnitsPreferenceDisposable?.dispose()
                    mSetUnitsPreferenceDisposable = d
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        }

        mBinding.mapsDisplayModeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val preference = when(checkedId) {
                R.id.traditionalMapsButton -> Preferences.MapsDisplayModePreference.kTraditional
                R.id.thumbnailMapsButton -> Preferences.MapsDisplayModePreference.kThumbnail
                else -> Preferences.MapsDisplayModePreference.kTraditional
            }

            mPreferences.SetMapsDisplayModePreference(this, preference).subscribe(object: SingleObserver<Int>{
                override fun onSuccess(t: Int) {}
                override fun onSubscribe(d: Disposable)
                {
                    mSetMapsDisplayModePreferenceDisposable?.dispose()
                    mSetMapsDisplayModePreferenceDisposable = d
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        }

        mBinding.radarColorThemeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val preference = when(checkedId) {
                R.id.lightThemeButton -> Preferences.RadarColorThemePreference.kLight
                R.id.darkThemeButton -> Preferences.RadarColorThemePreference.kDark
                else -> Preferences.RadarColorThemePreference.kLight
            }

            mPreferences.SetRadarColorThemePreference(this, preference).subscribe(object: SingleObserver<Int> {
                override fun onSuccess(t: Int) {}
                override fun onSubscribe(d: Disposable) {
                    mSetRadarColorThemePreferenceDisposable?.dispose()
                    mSetRadarColorThemePreferenceDisposable = d
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        }
    }


    override fun onDestroy() {
        mPreferences.Dispose()
        mGetUnitsPreferenceDisposable?.dispose()
        mGetMapsDisplayModePreferenceDisposable?.dispose()
        mGetRadarColorThemePreferenceDisposable?.dispose()
        mSetUnitsPreferenceDisposable?.dispose()
        mSetMapsDisplayModePreferenceDisposable?.dispose()
        mSetRadarColorThemePreferenceDisposable?.dispose()
        mGetUnitsPreferenceDisposable = null
        mGetMapsDisplayModePreferenceDisposable = null
        mSetUnitsPreferenceDisposable = null
        mSetMapsDisplayModePreferenceDisposable = null
        super.onDestroy()
    }

    override fun NavigateToPage(inFragment: Fragment, inPushToBackStack: Boolean, inAnimationIn: Int, inAnimationOut: Int) {}
}