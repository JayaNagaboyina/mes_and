package org.mesonet.app.advisories.dependencyinjection;

import org.mesonet.app.advisories.AdvisoriesFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = AdvisoriesFragmentModule.class)



public interface AdvisoriesFragmentSubcomponent extends AndroidInjector<AdvisoriesFragment>
{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AdvisoriesFragment> {}
}
