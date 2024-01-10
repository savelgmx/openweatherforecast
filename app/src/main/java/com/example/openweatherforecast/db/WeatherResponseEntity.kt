package com.example.openweatherforecast.db

import androidx.room.*

import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_response")
@TypeConverters(Converters::class)
data class WeatherResponseEntity(
    @PrimaryKey
    var id: Int,
    var coordLon: Double,
    var coordLat: Double,
    var base: String,
    var mainTemp: Double,
    var mainFeelsLike: Double,
    var mainTempMin: Double,
    var mainTempMax: Double,
    var mainPressure: Int,
    var mainHumidity: Int,
    var visibility: Int,
    var windSpeed: Double,
    var windDeg: Int,
    var cloudsAll: Int,
    var dt: Long,
    var sysType: Int,
    var sysId: Int,
    var sysMessage: Double,
    var sysCountry: String,
    var sysSunrise: Long,
    var sysSunset: Long,
    var timezone: Int,
    var name: String,
    var cod: Int
) {
    @Relation(parentColumn = "id", entityColumn = "weatherResponseId")
    var weather: List<WeatherEntity> = listOf()
}
