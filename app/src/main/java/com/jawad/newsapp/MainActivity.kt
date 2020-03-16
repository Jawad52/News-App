package com.jawad.newsapp

import androidx.fragment.app.Fragment
import com.jawad.newsapp.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initializeViewModel() {

    }
}
