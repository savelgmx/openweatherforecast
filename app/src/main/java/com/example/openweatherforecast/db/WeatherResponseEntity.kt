package com.example.openweatherforecast.db

import androidx.room.*

import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_response")
@TypeConverters(Converters::class)
data class WeatherResponseEntity(
    @PrimaryKey
    val id: Int,
    val coordLon: Double,
    val coordLat: Double,
    val base: String,
    val mainTemp: Double,
    val mainFeelsLike: Double,
    val mainTempMin: Double,
    val mainTempMax: Double,
    val mainPressure: Int,
    val mainHumidity: Int,
    val visibility: Int,
    val windSpeed: Double,
    val windDeg: Int,
    val cloudsAll: Int,
    val dt: Long,
    val sysType: Int,
    val sysId: Int,
    val sysMessage: Double,
    val sysCountry: String,
    val sysSunrise: Long,
    val sysSunset: Long,
    val timezone: Int,
    val name: String,
    val cod: Int
) {
    @Relation(parentColumn = "id", entityColumn = "weatherResponseId")
    val weather: List<WeatherEntity> = listOf()
}
