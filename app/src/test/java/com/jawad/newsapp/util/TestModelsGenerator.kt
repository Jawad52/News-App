package com.jawad.newsapp.util

import com.jawad.newsapp.data.local.model.NewsItem
import java.util.ArrayList

/**
 * The class TestUnit
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 21 Mar 2020
 */

class TestModelsGenerator {

    fun generateNewsModel(stup: String): List<NewsItem> {
        val newsItems = ArrayList<NewsItem>()
        for (i in 0..24) {
            newsItems.add(generateNewsItemModel(stup))
        }
        return newsItems
    }

    fun generateNewsModelWithEmptyList(): List<NewsItem> {
        return emptyList()
    }

    fun generateNewsItemModel(stup: String): NewsItem {
        val newsModelItem = NewsItem()
        newsModelItem.title = stup
        newsModelItem.abstractText = stup
        newsModelItem.byline = stup
        newsModelItem.createdDate = stup
        newsModelItem.itemType = stup
        newsModelItem.kicker = stup
        newsModelItem.materialTypeFacet = stup
        newsModelItem.publishedDate = stup
        newsModelItem.section = stup
        newsModelItem.shortUrl = stup
        newsModelItem.subsection = stup
        newsModelItem.updatedDate = stup
        newsModelItem.url = stup
        newsModelItem.uri = stup
        newsModelItem.multimedia = emptyList()
        return newsModelItem
    }
}