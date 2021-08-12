package org.mesonet.app.maps.dependencyinjection;

import org.mesonet.app.maps.MapListFragment;
import org.mesonet.core.PerFragment;

import androidx.fragment.app.Fragment;
import dagger.Binds;
import dagger.Module;


@Module
abstract class MapListFragmentModule
{
    @Binds
    @PerFragment
    abstract Fragment Fragment(MapListFragment inMapsListFragment);
}
