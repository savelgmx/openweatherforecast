package com.example.openweatherforecast.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun setIntModel(ints: List<CurrentWeatherEntity>): String = Gson().toJson(ints)

    @TypeConverter
    fun getIntModel(jsonModel: String): List<CurrentWeatherEntity> {
        val listType: Type = object : TypeToken<List<CurrentWeatherEntity>>() {}.type
        return Gson().fromJson(jsonModel, listType)
    }


}