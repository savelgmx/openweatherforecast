package com.example.openweatherforecast.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_response")
data class WeatherResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("coord")
    @Embedded val coord: CoordEntity,
    @SerializedName("weather")
    @Embedded val weather: List<WeatherEntity>,
    @SerializedName("base")
    val base: String,
    @SerializedName("main")
    @Embedded val main: MainEntity,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("wind")
    @Embedded val wind: WindEntity,
    @SerializedName("clouds")
    @Embedded val clouds: CloudsEntity,
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("sys")
    @Embedded val sys: SysEntity,
    @SerializedName("timezone")
    val timezone: Int,
/*
    @SerializedName("id")
    val id: Int,
*/
    @SerializedName("name")
    val name: String,
    @SerializedName("cod")
    val cod: Int
)

data class CoordEntity(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)
data class WeatherEntity (
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
        )

data class MainEntity(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

data class WindEntity(
    val speed: Double,
    val deg: Int
)

data class CloudsEntity(
    val all: Int
)

data class SysEntity(
    val type: Int,
    val id: Int,
    val message: Double,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
