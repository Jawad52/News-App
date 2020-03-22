package com.jawad.newsapp.data.remote

import com.google.gson.annotations.SerializedName

data class ResultsResponse<T>(
    @SerializedName("status")
    val status: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("section")
    val section: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<T>
)