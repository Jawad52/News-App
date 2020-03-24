package com.jawad.newsapp.data

import com.jawad.newsapp.data.remote.Result
import com.jawad.newsapp.data.local.LocalRepository
import com.jawad.newsapp.data.local.model.NewsItem
import com.jawad.newsapp.data.remote.dataSource.RemoteDataSource
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The class DataRepository
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 *
 * Data Repository module for handling data operations.
 */

@Singleton
class DataRepository @Inject
constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localRepository: LocalRepository
) {

    /**
     * API response handled here
     * Stored News Model list in the room db
     * returned News list from database on offline mode
     * @param scope
     * @param callback news list to HomeViewmodel
     */
    fun observeNews(
        scope: CoroutineScope,
        callback: (Result<List<NewsItem>>) -> Unit
    ) {
        scope.launch(Dispatchers.IO + getJobErrorHandler()) {
            callback(Result.loading())
            val response = remoteDataSource.fetchNews()
            if (response.status == Result.Status.SUCCESS) {
                localRepository.saveNews(response.data!!.results)
                callback(Result.success(localRepository.getNewsList()))
            } else if (response.status == Result.Status.ERROR) {
                if (localRepository.getNewsListSize() > 0)
                    callback(Result.success(localRepository.getNewsList()))
                else
                    callback(Result.error(response.message!!))
            }
        }
    }


    fun observeNewsList(
        connectivityAvailable: Boolean,
        coroutineScope: CoroutineScope
    ) = runBlocking {
        if (connectivityAvailable) observeRemoteNewsList(coroutineScope)
        else observeLocalNewsList()
    }

    private fun observeLocalNewsList(): List<NewsItem> {
        return localRepository.getNewsList()
    }

    private suspend fun observeRemoteNewsList(ioCoroutineScope: CoroutineScope)
            : List<NewsItem> {
        val getNewsList =
            ioCoroutineScope.async(Dispatchers.IO + getJobErrorHandler()) {
                val response = remoteDataSource.fetchNews()
                if (response.status == Result.Status.SUCCESS) {
                    localRepository.saveNews(response.data!!.results)
                } else if (response.status == Result.Status.ERROR) {
                    if (localRepository.getNewsListSize() == 0)
                        emptyArray<NewsItem>()
                }
                localRepository.getNewsList()
            }
        return getNewsList.await()
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
    }
}