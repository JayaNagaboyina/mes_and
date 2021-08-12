package org.mesonet.app.advisories.dependencyinjection;


import org.mesonet.app.advisories.AdvisoriesFragment;
import org.mesonet.core.PerFragment;

import androidx.fragment.app.Fragment;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class AdvisoriesFragmentModule
{
    @Binds
    @PerFragment
    abstract Fragment Fragment(AdvisoriesFragment inAdvisoriesFragment);
}
