package org.mesonet.app.dependencyinjection;

import android.content.Context;

import org.mesonet.app.Application;
import org.mesonet.app.widget.dependencyinjection.WidgetProviderLargeSubcomponent;
import org.mesonet.app.widget.dependencyinjection.WidgetProviderSubcomponent;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {WidgetProviderSubcomponent.class,
                         WidgetProviderLargeSubcomponent.class})
public abstract class ApplicationContextModule
{
    @Binds
    @Singleton
    abstract android.app.Application Application(Application inApplication);
}

