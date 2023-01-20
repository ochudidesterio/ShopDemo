package com.virus.expressshop.data.coverters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.virus.expressshop.data.Categories
import com.virus.expressshop.data.Rating

@TypeConverters
class RatingTypeCoverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): Rating {
        return gson.fromJson(value, Rating::class.java)
    }

    @TypeConverter
    fun toString(rating: Rating): String {
        return gson.toJson(rating)
    }
    @TypeConverter
    fun fromStringToArrayList(value: String):ArrayList<String>{
        return gson.fromJson(value,Categories::class.java)
    }
    @TypeConverter
    fun fromArrayListToString(list:ArrayList<String>):String{
        return gson.toJson(list)
    }
}