package com.jawad.newsapp.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
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

    @field:SerializedName("id")
    val id: Long,

    @PrimaryKey
    @field:SerializedName("title")
    var title: String?,

    @field:SerializedName("child_caption")
    var childCaption: String?,

    @field:SerializedName("abstract")
    var abstract: String?,

    @field:SerializedName("byline")
    var byline: String?,

    @field:SerializedName("created_date")
    var createdDate: String?,

    @field:SerializedName("des_facet")
    var desFacet: List<String?>?,

    @field:SerializedName("geo_facet")
    var geoFacet: List<String?>?,

    @field:SerializedName("item_type")
    var itemType: String?,

    @field:SerializedName("kicker")
    var kicker: String?,

    @field:SerializedName("material_type_facet")
    var materialTypeFacet: String?,

    @field:SerializedName("multimedia")
    var multimedia: List<Multimedia?>?,

    @field:SerializedName("org_facet")
    var orgFacet: List<String>?,

    @field:SerializedName("per_facet")
    var perFacet: List<String>?,

    @field:SerializedName("published_date")
    var publishedDate: String?,

    @field:SerializedName("section")
    var section: String?, // us

    @field:SerializedName("short_url")
    var shortUrl: String?,

    @field:SerializedName("subsection")
    var subsection: String?,

    @field:SerializedName("updated_date")
    var updatedDate: String?,

    @field:SerializedName("uri")
    var uri: String?
)

data class NewsEntity(
    @Embedded
    var newsItem: NewsItem,
    @Relation(
        parentColumn = "child_caption",
        entityColumn = "caption",
        entity = Multimedia::class
    ) var multimedia: List<Multimedia>
)