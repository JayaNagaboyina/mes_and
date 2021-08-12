package org.mesonet.app.site.mesonetdata.dependencyinjection;

import org.mesonet.dataprocessing.site.mesonetdata.MesonetUIController;
import org.mesonet.core.PerChildFragment;

import dagger.Subcomponent;


@PerChildFragment
@Subcomponent(modules = {MesonetUIControllerModule.class})
public interface MesonetUIControllerSubcomponent
{
    void Inject(MesonetUIController inMesonetUIController);

    @Subcomponent.Builder
    abstract class Builder
    {
        abstract MesonetUIControllerSubcomponent GetSubcomponent();
    }
}
