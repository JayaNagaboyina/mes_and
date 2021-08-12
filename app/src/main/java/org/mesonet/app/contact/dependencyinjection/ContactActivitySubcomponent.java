package org.mesonet.app.contact.dependencyinjection;

import org.mesonet.app.contact.ContactActivity;
import org.mesonet.core.PerContext;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@PerContext
@Subcomponent(modules = {ContactActivityModule.class})
public interface ContactActivitySubcomponent extends AndroidInjector<ContactActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ContactActivity> {
    }
}
