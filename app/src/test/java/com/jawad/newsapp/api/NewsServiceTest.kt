package com.jawad.newsapp.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jawad.newsapp.data.remote.NewsService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The class NewsServiceTest
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 22 Mar 2020
 */

@RunWith(JUnit4::class)
class NewsServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: NewsService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @Test
    fun requestNewsList() {
        runBlocking {
            enqueueResponse("newslist.json")
            val resultResponse = service.fetchNews().body()

            val request = mockWebServer.takeRequest()
            Assert.assertNotNull(resultResponse)
            Assert.assertThat(request.path, CoreMatchers.`is`("/topstories/v2/home.json?api-key=4rfwOLzLTWd1a5xixcPjwddAhw3p0eiF"))
        }
    }

    @Test
    fun getNewsModelResponse() {
        runBlocking {
            enqueueResponse("newslist.json")
            val resultResponse = service.fetchNews().body()
            val newsModel = resultResponse!!.results

            Assert.assertThat(resultResponse.numResults, CoreMatchers.`is`(54))
            Assert.assertThat(newsModel.size, CoreMatchers.`is`(54))
        }
    }

    @Test
    fun getNewsItem() {
        runBlocking {
            enqueueResponse("newslist.json")
            val resultResponse = service.fetchNews().body()
            val newsItems = resultResponse!!.results

            val newsItem = newsItems[0]
            Assert.assertThat(newsItem.title, CoreMatchers.`is`("Governments and Companies Race to Make Masks Vital to Virus Fight"))
            Assert.assertThat(newsItem.section, CoreMatchers.`is`("business"))
            Assert.assertThat(newsItem.updatedDate, CoreMatchers.`is`("2020-03-22T01:28:22-04:00"))
            Assert.assertThat(newsItem.createdDate, CoreMatchers.`is`("2020-03-21T19:54:35-04:00"))
            Assert.assertThat(newsItem.publishedDate, CoreMatchers.`is`("2020-03-21T19:54:35-04:00"))
            Assert.assertThat(newsItem.itemType, CoreMatchers.`is`("Article"))
            Assert.assertThat(
                newsItem.uri,
                CoreMatchers.`is`("nyt://article/1aed6e29-82e2-5356-9961-e58608702788")
            )
            Assert.assertThat(
                newsItem.url,
                CoreMatchers.`is`("https://www.nytimes.com/2020/03/21/business/coronavirus-masks-hanes-trump.html")
            )
            Assert.assertThat(
                newsItem.multimedia!![0].url,
                CoreMatchers.`is`("https://static01.nyt.com/images/2020/03/22/us/politics/21JPvirus-masks1-print/merlin_170307744_833b3ae1-26a2-4ee1-a5b9-79abb5cd49e8-superJumbo.jpg")
            )
            Assert.assertThat(
                newsItem.multimedia!![0].format,
                CoreMatchers.`is`("superJumbo")
            )
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
            source.readString(Charsets.UTF_8))
        )
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}