package org.mesonet.app.widget.dependencyinjection;

import android.content.Context;

import org.mesonet.app.Application;
import org.mesonet.app.widget.EmptyLocationProvider;
import org.mesonet.cache.Cacher;
import org.mesonet.cache.CacherImpl;
import org.mesonet.cache.site.SiteCache;
import org.mesonet.cache.site.SiteCacheImpl;
import org.mesonet.core.PerContext;
import org.mesonet.dataprocessing.ConnectivityStatusProvider;
import org.mesonet.dataprocessing.ConnectivityStatusProviderImpl;
import org.mesonet.dataprocessing.LocationProvider;
import org.mesonet.dataprocessing.site.MesonetSiteDataController;
import org.mesonet.dataprocessing.site.MesonetSiteDataControllerImpl;
import org.mesonet.dataprocessing.site.forecast.FiveDayForecastDataController;
import org.mesonet.dataprocessing.site.forecast.FiveDayForecastDataControllerImpl;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetDataController;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetDataControllerImpl;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetUIController;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetUIControllerImpl;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class WidgetProviderLargeModule
{
    @Binds
    @PerContext
    abstract LocationProvider LocationProvider(EmptyLocationProvider inEmptyLocationProvider);

    @Binds
    @PerContext
    abstract Context Context(Application inContext);

    @Binds
    @PerContext
    abstract MesonetSiteDataController MesonetSiteDataController(MesonetSiteDataControllerImpl inProvider);

    @Binds
    @PerContext
    abstract MesonetDataController MesonetDataController(MesonetDataControllerImpl inProvider);

    @Binds
    @PerContext
    abstract MesonetUIController MesonetUIController(MesonetUIControllerImpl inProvider);

    @Binds
    @PerContext
    abstract FiveDayForecastDataController FiveDataForecastDataController(FiveDayForecastDataControllerImpl inProvider);

    @Binds
    @PerContext
    abstract SiteCache SiteCache(SiteCacheImpl inCache);

    @Binds
    @PerContext
    abstract Cacher Cacher(CacherImpl inCache);


    @Binds
    @PerContext
    abstract ConnectivityStatusProvider ConnectivityStatusProvider(ConnectivityStatusProviderImpl inStatusProvider);
}
