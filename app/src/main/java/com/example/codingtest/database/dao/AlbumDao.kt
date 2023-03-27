package com.example.codingtest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.codingtest.retrofit.model.Album2

@Dao
interface AlbumDao {
    @Query("Select * From Album2")
    fun getAllAsync(): List<Album2>

    @Query("Select * From Album2 Where isBookmarked = 1")
    fun getBookmarked(): List<Album2>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Album2)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<Album2>): List<Long>

    @Query("UPDATE Album2 SET isBookmarked = 0 WHERE collectionId = :collectionId")
    fun removeBookmarked(collectionId: String)

//    @Update(entity = Album2::class)
//    fun addBookmarked(album2: Album2)

    @Query("UPDATE Album2 SET isBookmarked = 1 WHERE collectionId = :collectionId")
    fun addBookmarked(collectionId: String)

    @Query("Delete From Album2")
    fun deleteAllAsync()

    @Transaction
    fun deleteAndInsert(data: List<Album2>): Boolean {
        deleteAllAsync()
        val insertedResult = insertAll(data)

        return insertedResult.size == data.size
    }
}