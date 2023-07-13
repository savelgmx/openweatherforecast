package com.example.openweatherforecast.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    //List<WeatherDetailsEntity>
    fun setIntModel(ints: List<WeatherEntity>): String = Gson().toJson(ints)

    @TypeConverter
    fun getIntModel(jsonModel: String): List<WeatherEntity> {
        val listType: Type = object : TypeToken<List<WeatherEntity>>() {}.type
        return Gson().fromJson(jsonModel, listType)
    }


}