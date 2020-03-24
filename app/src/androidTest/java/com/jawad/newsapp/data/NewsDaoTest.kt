package com.jawad.newsapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jawad.newsapp.data.local.dao.NewsDao
import com.jawad.newsapp.util.testNewsListA
import com.jawad.newsapp.util.testNewsListB
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The class NewsDaoTest
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 21 Mar 2020
 */

@RunWith(AndroidJUnit4::class)
class NewsDaoTest : DbTest() {
    private lateinit var newsDao: NewsDao
    private val setNewsItemA = testNewsListA.copy()
    private val setNewsItemB = testNewsListB.copy()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        newsDao = db.newsDao()
        // Insert News Items into database
        runBlocking {
            newsDao.insertAllNewsItem(listOf(setNewsItemA, setNewsItemB))
        }
    }

    @Test

    fun testGetItem() {
        val newList = newsDao.getNews()
        assertThat(newList.size, equalTo(2))

        //Ensure that new list in date order
        assertThat(newList[0], equalTo(setNewsItemA))
        assertThat(newList[1], equalTo(setNewsItemB))
    }

    @Test
    fun testGetNewsItem() {
        val newItem = newsDao.getNewsById(setNewsItemA.id)
        assertThat(newItem, Matchers.equalTo(setNewsItemA))
    }
}