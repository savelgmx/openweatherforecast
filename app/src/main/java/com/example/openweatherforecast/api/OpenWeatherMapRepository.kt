package com.example.openweatherforecast.api

import androidx.lifecycle.LiveData
import com.example.openweatherforecast.core.utils.Resource
import com.example.openweatherforecast.db.CurrentWeatherEntity
import com.example.openweatherforecast.response.WeatherResponse

interface OpenWeatherMapRepository {
    suspend fun getWeatherForecast(city: String): Resource<WeatherResponse>
    suspend fun getWeatherForecastFromDB(): LiveData<List<CurrentWeatherEntity>>
}
