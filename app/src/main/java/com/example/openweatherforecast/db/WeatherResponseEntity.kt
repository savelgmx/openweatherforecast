package com.example.openweatherforecast.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_response")
@TypeConverters(Converters::class)
data class WeatherResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cityName: String = "",
    val countryCode: String = "",
     val weatherDetails: List<WeatherDetailsEntity> = emptyList()
)

@Entity(tableName = "weather_details")
data class WeatherDetailsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val main: String = "",
    val description: String = "",
    val icon: String = ""
)