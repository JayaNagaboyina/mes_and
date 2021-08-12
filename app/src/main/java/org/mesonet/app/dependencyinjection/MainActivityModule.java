package org.mesonet.app.dependencyinjection;

import android.app.Activity;
import android.content.Context;

import org.mesonet.androidsystem.DeviceLocation;
import org.mesonet.app.MainActivity;
import org.mesonet.app.advisories.AdvisoriesFragment;
import org.mesonet.app.advisories.dependencyinjection.AdvisoriesFragmentSubcomponent;
import org.mesonet.app.baseclasses.BaseActivity;
import org.mesonet.app.maps.MapListFragment;
import org.mesonet.app.maps.dependencyinjection.MapListFragmentSubcomponent;
import org.mesonet.app.radar.RadarFragment;
import org.mesonet.app.radar.dependencyinjection.RadarFragmentSubcomponent;
import org.mesonet.app.site.SiteOverviewFragment;
import org.mesonet.app.site.dependencyinjection.SiteOverviewFragmentSubcomponent;
import org.mesonet.cache.Cacher;
import org.mesonet.cache.CacherImpl;
import org.mesonet.cache.site.SiteCache;
import org.mesonet.cache.site.SiteCacheImpl;
import org.mesonet.core.PerContext;
import org.mesonet.dataprocessing.ConnectivityStatusProvider;
import org.mesonet.dataprocessing.ConnectivityStatusProviderImpl;
import org.mesonet.dataprocessing.LocationProvider;
import org.mesonet.dataprocessing.radar.RadarDataController;
import org.mesonet.dataprocessing.radar.RadarImageDataProvider;
import org.mesonet.dataprocessing.radar.RadarSiteDataProvider;
import org.mesonet.dataprocessing.radar.RadarSiteDataProviderImpl;
import org.mesonet.dataprocessing.site.MesonetSiteDataController;
import org.mesonet.dataprocessing.site.MesonetSiteDataControllerImpl;
import org.mesonet.dataprocessing.site.forecast.FiveDayForecastDataController;
import org.mesonet.dataprocessing.site.forecast.FiveDayForecastDataControllerImpl;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetDataController;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetDataControllerImpl;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetUIController;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetUIControllerImpl;

import androidx.fragment.app.Fragment;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;



@Module(includes = AndroidSupportInjectionModule.class,
        subcomponents = {SiteOverviewFragmentSubcomponent.class,
        MapListFragmentSubcomponent.class,
        RadarFragmentSubcomponent.class,
        AdvisoriesFragmentSubcomponent.class})
public abstract class MainActivityModule
{
    @Binds
    @IntoMap
    @FragmentKey(SiteOverviewFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    SiteOverviewFragmentInjectorFactory(SiteOverviewFragmentSubcomponent.Builder inBuilder);

    @Binds
    @IntoMap
    @FragmentKey(MapListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    MapListFragmentInjectorFactory(MapListFragmentSubcomponent.Builder inBuilder);

    @Binds
    @IntoMap
    @FragmentKey(RadarFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    RadarFragmentInjectorFactory(RadarFragmentSubcomponent.Builder inBuilder);

    @Binds
    @IntoMap
    @FragmentKey(AdvisoriesFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    AdvisoriesFragmentInjectorFactory(AdvisoriesFragmentSubcomponent.Builder inBuilder);

    @Binds
    @PerContext
    abstract Activity Activity(MainActivity inMainActivity);

    @Binds
    @PerContext
    abstract Context Context(MainActivity inContext);

    @Binds
    @PerContext
    abstract BaseActivity BaseActivity(MainActivity inMainActivity);

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
    abstract RadarImageDataProvider RadarImageDataProvider(RadarDataController inRadarDataController);

    @Binds
    @PerContext
    abstract RadarSiteDataProvider RadarSiteDataProvider(RadarSiteDataProviderImpl inSiteDataProvider);

    @Binds
    @PerContext
    abstract LocationProvider LocationProvider(DeviceLocation inDeviceLocation);

    @Binds
    @PerContext
    abstract ConnectivityStatusProvider ConnectivityStatusProvider(ConnectivityStatusProviderImpl inStatusProvider);
}