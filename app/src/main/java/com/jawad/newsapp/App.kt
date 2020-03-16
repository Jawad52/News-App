package com.jawad.newsapp

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication
import com.jawad.newsapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * The class App
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 14 Mar 2020
 */

class App : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        app = WeakReference(this)
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    companion object {
        private lateinit var app: WeakReference<App>
        fun getInstance(): App? {
            return app.get()
        }
    }
}