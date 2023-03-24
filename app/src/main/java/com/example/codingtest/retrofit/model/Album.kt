package com.example.codingtest.retrofit.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Album(
//    @SerializedName("wrapperType")
//    val wrapperType: String,
//    @SerializedName("collectionType")
//    val collectionType: String,
//    @SerializedName("artistId")
//    val artistId: Int,
//    @SerializedName("collectionId")
//    val collectionId: Int,
//    @SerializedName("amgArtistId")
//    val amgArtistId: Int,
//    @SerializedName("artistName")
//    val artistName: String,
//    @SerializedName("collectionName")
//    val collectionName: String,
//    @SerializedName("collectionCensoredName")
//    val collectionCensoredName: String,
//    @SerializedName("artistViewUrl")
//    val artistViewUrl: String,
//    @SerializedName("collectionViewUrl")
//    val collectionViewUrl: String,
//    @SerializedName("artworkUrl60")
//    val artworkUrl60: String,
//    @SerializedName("artworkUrl100")
//    val artworkUrl100: String,
//    @SerializedName("collectionPrice")
//    val collectionPrice: Double,
//    @SerializedName("collectionExplicitness")
//    val collectionExplicitness: String,
//    @SerializedName("trackCount")
//    val trackCount: Int,
//    @SerializedName("copyright")
//    val copyright: String,
//    @SerializedName("country")
//    val country: String,
//    @SerializedName("currency")
//    val currency: String,
//    @SerializedName("releaseDate")
//    val releaseDate: String,
//    @SerializedName("primaryGenreName")
//    val primaryGenreName: String,
//    var isBookmarked: Boolean = false

    val wrapperType: String,
    val collectionType: String,
    val artistId: Int,
    @PrimaryKey
    val collectionId: Int,
    val amgArtistId: Int,
    val artistName: String,
    val collectionName: String,
    val collectionCensoredName: String,
    val artistViewUrl: String,
    val collectionViewUrl: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: Double,
    val collectionExplicitness: String,
    val trackCount: Int,
    val copyright: String,
    val country: String,
    val currency: String,
    val releaseDate: String,
    val primaryGenreName: String,
    var isBookmarked: Boolean = false
)
