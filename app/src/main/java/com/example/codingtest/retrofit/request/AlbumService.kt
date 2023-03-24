package com.example.codingtest.retrofit.request

import com.example.codingtest.retrofit.model.SearchResult
import com.example.codingtest.retrofit.model.SearchResult2
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
//    @GET("search")
//    suspend fun searchAlbums(
//        @Query("term") searchTerm: String,
//        @Query("entity") entity: String
//    ): Response<SearchResult>

    @GET("search")
    fun searchAlbums(
        @Query("term") searchTerm: String,
        @Query("entity") entity: String
    ): Call<SearchResult2>
}