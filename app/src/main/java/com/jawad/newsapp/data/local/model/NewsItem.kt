package com.jawad.newsapp.data.local.model

import androidx.room.*
import com.jawad.newsapp.data.local.Converters
import com.jawad.newsapp.data.remote.dto.Multimedia

/**
 * The class NewsModel
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 */
@Entity(tableName = "news")
data class NewsItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var title: String,

    var abstractText: String,

    var byline: String,

    var createdDate: String,

    var itemType: String,

    var kicker: String,

    var materialTypeFacet: String,

    var publishedDate: String,

    var section: String,

    var shortUrl: String,

    var subsection: String,

    var updatedDate: String,

    var url: String,

    var uri: String,

    var multimedia: List<Multimedia>
) {
    constructor() : this(
        0, "", "", "", "",
        "", "", "", "", "", "", "",
        "", "", "", emptyList()
    )
}