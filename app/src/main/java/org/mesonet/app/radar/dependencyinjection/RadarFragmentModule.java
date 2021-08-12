package org.mesonet.app.radar.dependencyinjection;

import org.mesonet.app.filterlist.FilterListFragment;
import org.mesonet.app.filterlist.dependencyinjection.FilterListFragmentSubcomponent;
import org.mesonet.app.radar.RadarFragment;
import org.mesonet.core.PerFragment;
import org.mesonet.dataprocessing.SelectSiteListener;
import org.mesonet.dataprocessing.filterlist.FilterListDataProvider;
import org.mesonet.dataprocessing.radar.RadarSiteDataProvider;

import androidx.fragment.app.Fragment;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;


@Module(subcomponents = FilterListFragmentSubcomponent.class)
abstract class RadarFragmentModule
{
    @Binds
    @PerFragment
    abstract Fragment Fragment(RadarFragment inRadarFragment);


    @Binds
    @IntoMap
    @FragmentKey(FilterListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    filterListFragmentInjectorFactory(FilterListFragmentSubcomponent.Builder builder);


    @Binds
    @PerFragment
    abstract FilterListFragment.FilterListCloser FilterListCloser(RadarFragment inRadarFragment);

    @Binds
    @PerFragment
    abstract FilterListDataProvider FilterListDataProvider(RadarSiteDataProvider inSiteDataProvider);


    @Binds
    @PerFragment
    abstract SelectSiteListener OnSelectedListener(RadarSiteDataProvider inSiteDataController);

}
