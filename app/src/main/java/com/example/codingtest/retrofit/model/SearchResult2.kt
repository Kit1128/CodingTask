package com.example.codingtest.retrofit.model

import com.google.gson.annotations.SerializedName

data class SearchResult2(
    val resultCount: Int,
    val results: List<Album2>

)