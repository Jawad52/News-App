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
    fun saveNews(newsModel: List<NewsModel>) {
        clearDatabase()
        val newsItemList: ArrayList<NewsItem> = arrayListOf()
        for (newsItem in newsModel) {
            val newsModelItem = NewsItem()
            newsModelItem.title = newsItem.title!!
            newsModelItem.abstractText = newsItem.abstract!!
            newsModelItem.byline = newsItem.byline!!
            newsModelItem.createdDate = newsItem.createdDate!!
            newsModelItem.itemType = newsItem.itemType!!
            newsModelItem.kicker = newsItem.kicker!!
            newsModelItem.materialTypeFacet = newsItem.materialTypeFacet
            newsModelItem.publishedDate = newsItem.publishedDate!!
            newsModelItem.section = newsItem.section!!
            newsModelItem.shortUrl = newsItem.shortUrl!!
            newsModelItem.subsection = newsItem.subsection!!
            newsModelItem.updatedDate = newsItem.updatedDate!!
            newsModelItem.url = newsItem.url!!
            newsModelItem.uri = newsItem.uri!!
            newsModelItem.multimedia = newsItem.multimedia
            newsItemList.add(newsModelItem)
        }
        dao.insertAllNewsItem(newsItemList)
    }

    /**
     * Get news data by news ID
     * @param newId
     */
    fun getNewsById(newId: Long?) = dao.getNewsById(newId!!)

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