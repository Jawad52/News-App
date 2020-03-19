package com.jawad.newsapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jawad.newsapp.data.remote.dto.Multimedia
import java.lang.reflect.Type


/**
 * The class Converter
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 19 Mar 2020
 */

class Converters {

    @TypeConverter
    fun fromList(value: String?): List<Multimedia> {
        val listType: Type = object : TypeToken<List<Multimedia?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromString(list: List<Multimedia?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}