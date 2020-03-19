package com.jawad.newsapp.data.local.dao

import androidx.room.*
import com.jawad.newsapp.data.local.model.NewsItem

/**
 * The class NewsDao
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 */

@Dao
interface NewsDao {
    /**
     * Get all news item
     * @return List of NewsEntity
     */
    @Transaction
    @Query("SELECT * FROM news ORDER BY publishedDate DESC")
    fun getNews(): List<NewsItem>

    /**
     * Get news item by ID
     * @return NewsEntity
     */
    @Transaction
    @Query("SELECT * FROM news WHERE id =:newId")
    fun getNewsById(newId: Long): NewsItem

    /**
     *Get all news item
     * @return List of NewsEntity
     */
    @Transaction
    @Query("SELECT Count(*) FROM news")
    fun getNewsListSize(): Int

    /**
     * Insert all NewsItem
     * @param newsItemList inserted into news database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNewsItem(newsItemList: List<NewsItem>)

    /**
     * Delete all old News data
     */
    @Query("DELETE FROM news")
    fun deleteOldNews()
}