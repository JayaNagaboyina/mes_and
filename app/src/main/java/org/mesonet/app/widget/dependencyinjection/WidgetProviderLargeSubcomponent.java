package org.mesonet.app.widget.dependencyinjection;

import org.mesonet.app.widget.WidgetProviderLarge;
import org.mesonet.core.PerContext;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerContext
@Subcomponent(modules = WidgetProviderLargeModule.class)
public interface WidgetProviderLargeSubcomponent extends AndroidInjector<WidgetProviderLarge>
{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<WidgetProviderLarge> {
    }
}
