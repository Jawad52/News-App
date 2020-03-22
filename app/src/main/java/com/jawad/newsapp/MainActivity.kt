package com.jawad.newsapp

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.test.espresso.IdlingResource
import com.jawad.newsapp.ui.base.BaseActivity
import com.mindvalley.channels.util.EspressoIdlingResource
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initializeViewModel() {

    }
}
