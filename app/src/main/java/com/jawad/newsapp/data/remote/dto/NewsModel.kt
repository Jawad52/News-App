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

@Parcelize
data class NewsModel(
    @SerializedName("copyright")
    var copyright: String?, // Copyright (c) 2020 The New York Times Company. All Rights Reserved.
    @SerializedName("last_updated")
    var lastUpdated: String?, // 2020-03-15T02:04:38-04:00
    @SerializedName("num_results")
    var numResults: Int?, // 59
    @SerializedName("results")
    var results: List<NewsItem?>?,
    @SerializedName("section")
    var section: String?, // home
    @SerializedName("status")
    var status: String? // OK
) : Parcelable