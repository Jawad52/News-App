package com.jawad.newsapp.di

import com.google.gson.Gson
import dagger.Module
import com.jawad.newsapp.BuildConfig
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * The class CoreDataModule
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 *
 * Dagger module to provide core data functionality.
 */

@Module
class CoreDataModule {

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)
}