package org.mesonet.app.radar.dependencyinjection;

import org.mesonet.app.radar.RadarFragment;
import org.mesonet.core.PerFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;



@PerFragment
@Subcomponent(modules = RadarFragmentModule.class)
public interface RadarFragmentSubcomponent extends AndroidInjector<RadarFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RadarFragment> {
    }
}