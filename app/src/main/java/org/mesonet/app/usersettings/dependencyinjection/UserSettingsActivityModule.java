package org.mesonet.app.usersettings.dependencyinjection;

import android.app.Activity;
import android.content.Context;

import org.mesonet.androidsystem.DeviceLocation;
import org.mesonet.app.baseclasses.BaseActivity;
import org.mesonet.app.usersettings.UserSettingsActivity;
import org.mesonet.core.PerContext;
import org.mesonet.dataprocessing.LocationProvider;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class UserSettingsActivityModule
{
    @Binds
    @PerContext
    abstract Activity Activity(UserSettingsActivity inUserSettingsActivity);

    @Binds
    @PerContext
    abstract BaseActivity BaseActivity(UserSettingsActivity inUserSettingsActivity);

    @Binds
    @PerContext
    abstract Context Context(UserSettingsActivity inUserSettingsActivity);

    @Binds
    @PerContext
    abstract LocationProvider LocationProvider(DeviceLocation inDeviceLocation);
}
