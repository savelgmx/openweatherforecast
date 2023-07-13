package com.example.openweatherforecast.db

import androidx.room.Embedded
import androidx.room.Relation

data class WeatherResponseEntityWithWeather(
    @Embedded val weatherResponseEntity: WeatherResponseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "weatherResponseId"
    )
    val weather: List<WeatherEntity>
)
