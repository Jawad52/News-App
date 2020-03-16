package com.jawad.newsapp.data.remote

import javax.inject.Inject

/**
 * The class RemoteRepository
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 *
 * Works with the News API to get all the data from server.
 */

class RemoteDataSource @Inject constructor(private val service: NewsService) : BaseDataSource() {
    suspend fun fetchNews() = getResult { service.fetchNews() }
}