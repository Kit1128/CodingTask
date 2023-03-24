package com.example.codingtest.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringArrayConverter {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        if (value.isNullOrBlank()) return null
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>?): String? {
        if (list.isNullOrEmpty()) return null
        val gson = Gson()
        return gson.toJson(list)
    }
}