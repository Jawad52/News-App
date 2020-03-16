package com.jawad.newsapp.data.local.dao

import androidx.room.*
import com.jawad.newsapp.data.local.model.NewsItem
import com.jawad.newsapp.data.local.model.NewsEntity
import com.jawad.newsapp.data.remote.dto.Multimedia

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
     *Get all news item
     * @return List of NewsModel
     */
    @Transaction
    @Query("SELECT * FROM news ORDER BY publishedDate")
    fun getNews(): List<NewsEntity>

    /**
     *Get all news item
     * @return List of NewsModel
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
     *Insert all Multimedia
     * @param multimediaList inserted into multimedia database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMultimedia(multimediaList: List<Multimedia?>?)

    /**
     * Delete all old News data
     */
    @Query("DELETE FROM news")
    fun deleteOldNews()

    /**
     * Delete all old Multimedia data
     */
    @Query("DELETE FROM multimedia")
    fun deleteMultimedia()
}