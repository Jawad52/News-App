package com.jawad.newsapp.data.remote.dto

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Defined all the variables as @var, because it might happen that backend send some values as
 * null
 *
 * @Parcelize is from kotlin experimental but it is stable, and we use it for Parcelable
 * impersonation
 *
 * Convert your json to kotlin data class in easy simple way, by using
 * @(JSON To Kotlin Class) plugin in android studio, you can install the plugin as any other
 * plugin and then use it, for more details check here:
 * https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-
 */


data class NewsModel(
    
    @SerializedName("abstract")
    var abstract: String? = null, // Schools are closed. Events are canceled. If the virus keeps spreading, governments have even more disruptive options in mind.
    
    @SerializedName("byline")
    var byline: String? = null, // By Mike Baker and Miriam Jordan
    
    @SerializedName("created_date")
    var createdDate: String? = null, // 2020-03-13T05:00:24-04:00
    
    @SerializedName("des_facet")
    var desFacet: List<String>,
    
    @SerializedName("geo_facet")
    var geoFacet: List<String>,
    
    @SerializedName("item_type")
    var itemType: String? = null, // Article
    
    @SerializedName("kicker")
    var kicker: String? = null,
    
    @SerializedName("material_type_facet")
    var materialTypeFacet: String,
    
    @SerializedName("multimedia")
    var multimedia: List<Multimedia>?,
    
    @SerializedName("org_facet")
    var orgFacet: List<String>,
    
    @SerializedName("per_facet")
    var perFacet: List<String>,
    
    @SerializedName("published_date")
    var publishedDate: String? = null,// 2020-03-13T05:00:24-04:00
    
    @SerializedName("section")
    var section: String? = null, // us
    
    @SerializedName("short_url")
    var shortUrl: String? = null, // https://nyti.ms/33cBKRy
    
    @SerializedName("subsection")
    var subsection: String? = null,
    
    @SerializedName("title")
    var title: String? = null, // Efforts to Control the Coronavirus in the U.S. Could Get Even More Extreme
    
    @SerializedName("updated_date")
    var updatedDate: String? = null, // 2020-03-14T12:29:45-04:00
    
    @SerializedName("uri")
    var uri: String? = null, // nyt://article/7a4b8997-54e1-53ed-acc6-a020e79b4563
    
    @SerializedName("url")
    var url: String? = null // https://www.nytimes.com/2020/03/13/us/coronavirus-washington-quarantines.html
)