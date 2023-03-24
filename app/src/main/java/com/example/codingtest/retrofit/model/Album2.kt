package com.example.codingtest.retrofit.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Album2 (
    var collectionId: String,
    var wrapperType: String?,
    var collectionType: String?,
    var artistId: Int?,
    var amgArtistId: Int?,
    var artistName: String?,
    var collectionName: String?,
    var collectionCensoredName: String?,
    var artistViewUrl: String?,
    var collectionViewUrl: String?,
    var artworkUrl60: String?,
    var artworkUrl100: String?,
    var collectionPrice: Double?,
    var collectionExplicitness: String?,
    var trackCount: Int?,
    var copyright: String?,
    var country: String?,
    var currency: String?,
    var releaseDate: String?,
    var primaryGenreName: String?,
    var isBookmarked: Boolean = false
){
    @PrimaryKey(autoGenerate = true)
    var dbId: Int = 0
}