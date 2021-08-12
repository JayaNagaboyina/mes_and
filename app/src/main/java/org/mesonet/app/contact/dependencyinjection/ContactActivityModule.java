package org.mesonet.app.contact.dependencyinjection;

import android.app.Activity;

import org.mesonet.androidsystem.DeviceLocation;
import org.mesonet.app.baseclasses.BaseActivity;
import org.mesonet.app.contact.ContactActivity;
import org.mesonet.core.PerContext;
import org.mesonet.dataprocessing.LocationProvider;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class ContactActivityModule
{
    @Binds
    @PerContext
    abstract Activity Activity(ContactActivity inContactActivity);

    @Binds
    @PerContext
    abstract BaseActivity BaseActivity(ContactActivity inContactActivity);

    @Binds
    @PerContext
    abstract LocationProvider LocationProvider(DeviceLocation inDeviceLocation);
}
