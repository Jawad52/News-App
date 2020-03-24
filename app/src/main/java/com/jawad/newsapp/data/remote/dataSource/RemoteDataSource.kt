package com.jawad.newsapp.data.remote.dataSource

import com.jawad.newsapp.data.remote.BaseDataSource
import com.jawad.newsapp.data.remote.NewsService
import javax.inject.Inject

/**
 * The class RemoteDataSource
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 *
 * Works with the News API to get all the data from server.
 */
class RemoteDataSource @Inject constructor(private val newsService: NewsService) :
    BaseDataSource() {
    suspend fun fetchNews() = getResult { newsService.fetchNews() }
}
