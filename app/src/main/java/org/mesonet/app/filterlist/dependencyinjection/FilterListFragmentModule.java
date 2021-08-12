package org.mesonet.app.filterlist.dependencyinjection;

import org.mesonet.app.filterlist.FilterListFragment;
import org.mesonet.core.PerChildFragment;

import androidx.fragment.app.Fragment;
import dagger.Binds;
import dagger.Module;

@Module
abstract class FilterListFragmentModule
{
    @Binds
    @PerChildFragment
    abstract Fragment fragment(FilterListFragment inFilterListFragment);


}