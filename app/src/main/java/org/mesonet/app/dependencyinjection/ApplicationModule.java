package org.mesonet.app.dependencyinjection;

import android.app.Activity;
import android.content.BroadcastReceiver;

import org.mesonet.androidsystem.DeviceLocation;
import org.mesonet.androidsystem.Permissions;
import org.mesonet.androidsystem.PermissionsImpl;
import org.mesonet.androidsystem.UserSettings;
import org.mesonet.app.MainActivity;
import org.mesonet.app.widget.WidgetProvider;
import org.mesonet.app.contact.ContactActivity;
import org.mesonet.app.contact.dependencyinjection.ContactActivitySubcomponent;
import org.mesonet.app.usersettings.UserSettingsActivity;
import org.mesonet.app.usersettings.dependencyinjection.UserSettingsActivitySubcomponent;
import org.mesonet.app.widget.WidgetProviderLarge;
import org.mesonet.app.widget.dependencyinjection.WidgetProviderLargeSubcomponent;
import org.mesonet.app.widget.dependencyinjection.WidgetProviderSubcomponent;
import org.mesonet.cache.Cacher;
import org.mesonet.cache.CacherImpl;
import org.mesonet.cache.site.SiteCache;
import org.mesonet.cache.site.SiteCacheImpl;
import org.mesonet.cache.site.SiteListConverter;
import org.mesonet.cache.site.SiteListConverterImpl;
import org.mesonet.dataprocessing.LocationProvider;
import org.mesonet.dataprocessing.advisories.AdvisoryDataProvider;
import org.mesonet.dataprocessing.advisories.AdvisoryDataProviderImpl;
import org.mesonet.dataprocessing.advisories.AdvisoryDisplayListBuilder;
import org.mesonet.dataprocessing.advisories.AdvisoryDisplayListBuilderImpl;
import org.mesonet.dataprocessing.filterlist.FilterListController;
import org.mesonet.dataprocessing.filterlist.FilterListControllerImpl;
import org.mesonet.dataprocessing.formulas.UnitConverter;
import org.mesonet.dataprocessing.formulas.UnitConverterImpl;
import org.mesonet.dataprocessing.maps.MapsDataProvider;
import org.mesonet.dataprocessing.maps.MapsDataProviderImpl;
import org.mesonet.dataprocessing.radar.MapboxMapController;
import org.mesonet.dataprocessing.radar.MapboxMapControllerImpl;
import org.mesonet.dataprocessing.radar.RadarSiteDataProvider;
import org.mesonet.dataprocessing.radar.RadarSiteDataProviderImpl;
import org.mesonet.dataprocessing.site.MesonetSiteDataController;
import org.mesonet.dataprocessing.site.MesonetSiteDataControllerImpl;
import org.mesonet.dataprocessing.site.forecast.FiveDayForecastDataController;
import org.mesonet.dataprocessing.site.forecast.FiveDayForecastDataControllerImpl;
import org.mesonet.dataprocessing.site.mesonetdata.DerivedValues;
import org.mesonet.dataprocessing.site.mesonetdata.DerivedValuesImpl;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetDataController;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetDataControllerImpl;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetUIController;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetUIControllerImpl;
import org.mesonet.dataprocessing.userdata.Preferences;
import org.mesonet.models.radar.RadarDetailCreator;
import org.mesonet.models.radar.RadarDetailModelCreator;
import org.mesonet.network.DataProvider;
import org.mesonet.network.DataProviderImpl;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.BroadcastReceiverKey;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.multibindings.IntoMap;

@Module(includes = AndroidSupportInjectionModule.class,
        subcomponents = {MainActivitySubcomponent.class,
                         UserSettingsActivitySubcomponent.class,
                         ContactActivitySubcomponent.class,
                         WidgetProviderSubcomponent.class,
                         WidgetProviderLargeSubcomponent.class})
abstract class ApplicationModule
{
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    MainActivityInjectorFactory(MainActivitySubcomponent.Builder inBuilder);

    @Binds
    @IntoMap
    @ActivityKey(UserSettingsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    UserSettingsInjectorFactory(UserSettingsActivitySubcomponent.Builder inBuilder);

    @Binds
    @IntoMap
    @ActivityKey(ContactActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    ContactInjectorFactory(ContactActivitySubcomponent.Builder inBuilder);


    @Binds
    @IntoMap
    @BroadcastReceiverKey(WidgetProvider.class)
    abstract AndroidInjector.Factory<? extends BroadcastReceiver>
    WidgetProviderInjectorFactory(WidgetProviderSubcomponent.Builder inBuilder);


    @Binds
    @IntoMap
    @BroadcastReceiverKey(WidgetProviderLarge.class)
    abstract AndroidInjector.Factory<? extends BroadcastReceiver>
    WidgetProviderLargeInjectorFactory(WidgetProviderLargeSubcomponent.Builder inBuilder);

    @Binds
    abstract RadarDetailCreator RadarDetailCreator(RadarDetailModelCreator inCreator);

    @Binds
    abstract Preferences Preferences(UserSettings inSettings);

    @Binds
    abstract AdvisoryDataProvider AdvisoryDataProvider(AdvisoryDataProviderImpl inProvider);

    @Binds
    abstract DataProvider DataDownloader(DataProviderImpl inDownloader);

    @Binds
    abstract Permissions Permissions(PermissionsImpl inPermissions);

    @Binds
    abstract DerivedValues DerivedValues(DerivedValuesImpl inDerivedValues);

    @Binds
    abstract MapsDataProvider MapsDataProvider(MapsDataProviderImpl inMapsDataProvider);

    @Binds
    abstract MapboxMapController MapboxMapController(MapboxMapControllerImpl inMapboxMapController);

    @Binds
    abstract AdvisoryDisplayListBuilder AdvisoryDisplayListBuilder(AdvisoryDisplayListBuilderImpl inMapboxMapController);

    @Binds
    abstract FilterListController FilterListController(FilterListControllerImpl inFilterListController);

    @Binds
    abstract SiteListConverter SiteListConverter(SiteListConverterImpl inSiteListConverter);

    @Binds
    abstract UnitConverter UnitConverter(UnitConverterImpl inUnitConverter);
}