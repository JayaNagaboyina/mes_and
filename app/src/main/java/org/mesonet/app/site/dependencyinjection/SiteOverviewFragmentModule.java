package org.mesonet.app.site.dependencyinjection;

import org.mesonet.app.filterlist.FilterListFragment;
import org.mesonet.app.filterlist.dependencyinjection.FilterListFragmentSubcomponent;
import org.mesonet.app.site.SiteOverviewFragment;
import org.mesonet.core.PerFragment;
import org.mesonet.dataprocessing.SelectSiteListener;
import org.mesonet.dataprocessing.filterlist.FilterListDataProvider;
import org.mesonet.dataprocessing.site.MesonetSiteDataController;

import androidx.fragment.app.Fragment;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;



@Module(subcomponents = {FilterListFragmentSubcomponent.class})
abstract class SiteOverviewFragmentModule {

    // TODO (ContributesAndroidInjector) remove this in favor of @ContributesAndroidInjector
    @Binds
    @IntoMap
    @FragmentKey(FilterListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    filterListFragmentInjectorFactory(FilterListFragmentSubcomponent.Builder builder);

    @Binds
    @PerFragment
    abstract Fragment fragment(SiteOverviewFragment inSiteOverviewFragment);

    @Binds
    @PerFragment
    abstract FilterListFragment.FilterListCloser FilterListCloser(SiteOverviewFragment inSiteOverviewFragment);

    @Binds
    @PerFragment
    abstract FilterListDataProvider FilterListDataProvider(MesonetSiteDataController inSiteDataController);

    @Binds
    @PerFragment
    abstract SelectSiteListener OnSelectedListener(MesonetSiteDataController inSiteDataController);
}