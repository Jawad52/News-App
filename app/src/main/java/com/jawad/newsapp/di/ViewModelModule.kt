package com.jawad.newsapp.di

import androidx.lifecycle.ViewModel
import com.jawad.newsapp.ui.newsDetails.NewsDetailsViewModel
import com.jawad.newsapp.ui.newsList.NewsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * The class ViewModelModule
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 */

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun newsListViewModel(viewModel: NewsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailsViewModel::class)
    abstract fun newsDetailsViewModel(viewModel: NewsDetailsViewModel): ViewModel
}