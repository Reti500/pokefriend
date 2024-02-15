package com.example.local.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverters {
    @TypeConverter
    fun fromListOfString(data: List<String>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun fromStringToListOfStrings(data: String): List<String> {
        return Gson().fromJson(data, object : TypeToken<List<String>>() {}.type)
    }
}