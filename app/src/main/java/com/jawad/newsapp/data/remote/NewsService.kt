package com.jawad.newsapp.data.remote

import com.jawad.newsapp.data.remote.dto.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * The class NewsService
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 *
 * News REST API access points
 */

interface NewsService {
    @GET("topstories/v2/home.json?api-key=4rfwOLzLTWd1a5xixcPjwddAhw3p0eiF")
    suspend fun fetchNews(): Response<ResultsResponse<NewsModel>>
}