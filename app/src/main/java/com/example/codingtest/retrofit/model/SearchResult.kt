package com.example.codingtest.retrofit.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<Album>
)