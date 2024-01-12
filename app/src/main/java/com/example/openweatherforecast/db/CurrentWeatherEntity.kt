package com.example.openweatherforecast.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class CurrentWeatherEntity(
    @Embedded
    val coord: CoordEntity,
    @Embedded(prefix = "weather_")
    val weather: List<WeatherEntityItem>,
    val base: String,
    @Embedded
    val main: MainEntity,
    val visibility: Int,
    @Embedded
    val wind: WindEntity,
    @Embedded
    val clouds: CloudsEntity,
    val dt: Long,
    @Embedded
    val sys: SysEntity,
    val timezone: Int,
    @PrimaryKey
    val id: Int,
    val name: String,
    val cod: Int
)

data class CoordEntity(
    val lon: Double,
    val lat: Double
)

data class WeatherEntityItem(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
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