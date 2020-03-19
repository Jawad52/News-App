package com.jawad.newsapp.data

import com.jawad.newsapp.data.remote.Result
import com.jawad.newsapp.data.local.LocalRepository
import com.jawad.newsapp.data.local.model.NewsItem
import com.jawad.newsapp.data.remote.dataSource.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        scope.launch(Dispatchers.IO) {
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
}