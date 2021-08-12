package org.mesonet.app.baseclasses

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.fragment.app.Fragment

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector


abstract class BaseFragment : Fragment(), HasSupportFragmentInjector {
    @Inject
    internal lateinit var mChildFragmentInjector: DispatchingAndroidInjector<Fragment>

    /*
     override fun onAttach(inContext: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(inContext)
    }


    override fun onAttach(inActivity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(inActivity)
    }
     */
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }


    override fun onAttach(activity: Activity) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return mChildFragmentInjector
    }
}