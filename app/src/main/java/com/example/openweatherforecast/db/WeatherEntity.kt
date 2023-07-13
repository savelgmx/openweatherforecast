package com.example.openweatherforecast.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val weatherResponseId: Int,
    val weatherId: Int,
    val main: String,
    val description: String,
    val icon: String
)
