package org.mesonet.app.site.mesonetdata.dependencyinjection;

import org.mesonet.dataprocessing.site.mesonetdata.MesonetDataController;
import org.mesonet.dataprocessing.site.mesonetdata.MesonetUIController;
import org.mesonet.core.PerChildFragment;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class MesonetUIControllerModule
{
    @Binds
    @PerChildFragment
    abstract MesonetUIController MesonetUIController(MesonetUIController inMesonetUIController);

    @Binds
    @PerChildFragment
    abstract MesonetDataController BaseMesonetDataController(MesonetDataController inMesonetDataController);
}
