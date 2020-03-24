package com.jawad.newsapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jawad.newsapp.data.DataRepository
import com.jawad.newsapp.ui.newsList.NewsListViewModel
import com.jawad.newsapp.util.TestModelsGenerator
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

/**
 * The class NewsListViewModelTEst
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 23 Mar 2020
 */

@RunWith(JUnit4::class)
class NewsListViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private var coroutineScope = CoroutineScope(Dispatchers.IO)
    private var repository = mock(DataRepository::class.java)
    private var newsListViewModel: NewsListViewModel? = null
    private var testModelsGenerator: TestModelsGenerator = TestModelsGenerator()


    @Before
    fun setUp() {
        newsListViewModel = NewsListViewModel(repository, coroutineScope)
    }

    @Test
    fun testGetNews() {
        MatcherAssert.assertThat(newsListViewModel!!.connectivityAvailable, CoreMatchers.notNullValue())
        verify(repository, never())!!.observeNewsList(false, coroutineScope)
        verify(repository, never())!!.observeNewsList(true, coroutineScope)
    }

    @Test
    fun testGetNewsSuccessful(){
        verify(repository, never())!!.observeNewsList(true, coroutineScope)
    }

    @Test
    fun searchByTitleSuccess() {
        val stup = "news Title"
        val newList = testModelsGenerator.generateNewsModel(stup)
        val newsItem = newsListViewModel!!.searchByTitle(newList, stup)
        assertNotNull(newsItem)
        assertEquals(newsItem?.title, stup)
    }

    @Test
    fun searchByTitleFail() {
        val stupTitle = "news Title"
        val stupSearch = "search fail news title"
        val newsItem = newsListViewModel!!.searchByTitle(testModelsGenerator.generateNewsModel(stupTitle), stupSearch)
        assertEquals(newsItem, null)
    }
}