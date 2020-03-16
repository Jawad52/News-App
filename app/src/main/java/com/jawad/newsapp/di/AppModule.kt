package com.jawad.newsapp.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jawad.newsapp.data.local.AppDatabase
import com.jawad.newsapp.data.remote.NewsService
import com.jawad.newsapp.data.remote.dataSource.RemoteDataSource
import com.jawad.newsapp.util.Constants
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * The class AppModule
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 */

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideNewsService(@NewAPI okhttpClient: OkHttpClient,
                              converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, NewsService::class.java)

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsService: NewsService)
            = RemoteDataSource(newsService)

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideNewsDao(db: AppDatabase) = db.newsDao()

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @NewAPI
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder().build()
    }

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
                                   converterFactory: GsonConverterFactory, newsClass: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(newsClass)
    }
}