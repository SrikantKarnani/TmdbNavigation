package com.example.navigation.storage.db

import androidx.room.TypeConverter
import com.example.navigation.network.model.GenreResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



/**
 * Created by Srikant Karnani on 26/7/19.
 */
class Converter {

    @TypeConverter
    fun fromArrayListInt(list:ArrayList<Int>):String{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToIntArray(value: String): ArrayList<Int> {
        val listType = object : TypeToken<ArrayList<Int>>() {}.type
        return Gson().fromJson<ArrayList<Int>>(value, listType)
    }

    @TypeConverter
    fun fromArrayListString(list:ArrayList<String>):String{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToStringArray(value: String): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson<ArrayList<String>>(value, listType)
    }
}