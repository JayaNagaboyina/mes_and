package org.mesonet.app.maps.dependencyinjection;

import org.mesonet.app.maps.MapListFragment;
import org.mesonet.core.PerFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;



@PerFragment
@Subcomponent(modules = MapListFragmentModule.class)
public interface MapListFragmentSubcomponent extends AndroidInjector<MapListFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MapListFragment> {
    }
}