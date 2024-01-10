package com.example.openweatherforecast.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var weatherResponseId: Int,
    var weatherId: Int,
    var main: String,
    var description: String,
    var icon: String
)
