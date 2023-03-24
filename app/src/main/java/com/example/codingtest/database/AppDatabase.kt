package com.example.codingtest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.example.codingtest.database.converter.StringArrayConverter
import com.example.codingtest.database.dao.AlbumDao
import com.example.codingtest.retrofit.model.Album2

@Database(entities = [Album2::class], version = 1)
//@TypeConverter(StringArrayConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAlbumDao(): AlbumDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    context.packageName
                ).allowMainThreadQueries().build().also { instance = it }
            }
        }
    }
}