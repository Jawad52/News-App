package com.jawad.newsapp.di

import com.jawad.newsapp.ui.newsDetails.NewsDetailsFragment
import com.jawad.newsapp.ui.newsList.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * The class FragmentModuleBuilder
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 */

@Suppress("unused")
@Module
abstract class FragmentModuleBuilder {
    @ContributesAndroidInjector
    abstract fun contributeNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsDetailsFragment(): NewsDetailsFragment
}