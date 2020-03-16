package com.jawad.newsapp.data.local

import com.jawad.newsapp.data.local.dao.NewsDao
import com.jawad.newsapp.data.local.model.NewsItem
import com.jawad.newsapp.data.remote.dto.NewsModel
import javax.inject.Inject

/**
 * The class LocalRepository
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 *
 *
 */

class LocalRepository @Inject
constructor(private val dao: NewsDao) {

    /**
     * Insert all news data into database
     *
     * @param newsModel from api
     */
    fun saveNews(newsModel: NewsModel) {
        clearDatabase()
        val newsItemList: ArrayList<NewsItem> = arrayListOf()
        for (newsItem in newsModel.results!!) {

            val newsModelItem = NewsItem(id = 0,
                title = newsItem!!.title,
                abstract = newsItem.abstract,
                byline = newsItem.byline,
                childCaption = newsItem.multimedia!![0]!!.caption,
                createdDate = newsItem.createdDate!!,
                desFacet = newsItem.desFacet!!,
                geoFacet = newsItem.geoFacet!!,
                itemType = newsItem.itemType!!,
                kicker = newsItem.kicker!!,
                materialTypeFacet = newsItem.materialTypeFacet!!,
                multimedia = newsItem.multimedia!!,
                orgFacet = newsItem.orgFacet!!,
                perFacet = newsItem.perFacet!!,
                publishedDate = newsItem.publishedDate!!,
                section = newsItem.section!!,
                shortUrl = newsItem.shortUrl!!,
                subsection = newsItem.subsection!!,
                updatedDate = newsItem.updatedDate!!,
                uri = newsItem.uri!!
            )
            newsItemList.add(newsModelItem)
            dao.insertAllMultimedia(newsItem.multimedia)
        }
        dao.insertAllNewsItem(newsItemList)
    }

    /**
     * Get news data list
     */
    fun getNewsList() = dao.getNews()

    /**
     * Get news data list size
     */
    fun getNewsListSize() = dao.getNewsListSize()

    /**
     * Clear all data from database
     */
    private fun clearDatabase() {
        dao.deleteMultimedia()
        dao.deleteOldNews()
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: LocalRepository? = null

        fun getInstance(dao: NewsDao) =
            instance ?: synchronized(this) {
                instance
                    ?: LocalRepository(dao).also { instance = it }
            }
    }
}