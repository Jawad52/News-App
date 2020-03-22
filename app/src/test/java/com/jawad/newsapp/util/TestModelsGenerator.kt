package com.jawad.newsapp.util

import com.jawad.newsapp.data.remote.ResultsResponse
import com.jawad.newsapp.data.remote.dto.NewsModel
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

    fun generateNewsModel(stup: String): ResultsResponse<NewsModel> {
        val newsItems = ArrayList<NewsModel>()
        for (i in 0..24) {
            newsItems.add(generateNewsItemModel(stup))
        }
        return ResultsResponse(
            copyright = stup, section = stup, lastUpdated = stup,
            numResults = 25, results = newsItems, status = stup
        )
    }

    fun generateNewsModelWithEmptyList(stup: String): ResultsResponse<NewsModel> {
        val newsItems = ArrayList<NewsModel>()
        return ResultsResponse(
            copyright = stup, section = stup, lastUpdated = stup,
            numResults = 25, results = newsItems, status = stup
        )
    }

    fun generateNewsItemModel(stup: String): NewsModel {
        return NewsModel(
            title = stup,
            abstract = stup,
            url = stup,
            uri = stup,
            updatedDate = stup,
            subsection = stup,
            shortUrl = stup,
            section = stup,
            publishedDate = stup,
            perFacet = emptyList(),
            orgFacet = emptyList(),
            multimedia = emptyList(),
            materialTypeFacet = stup,
            kicker = stup,
            itemType = stup,
            geoFacet = emptyList(),
            desFacet = emptyList(),
            createdDate = stup,
            byline = stup
        )
    }
}