package com.example.openweatherforecast.api

import androidx.lifecycle.LiveData
import com.example.openweatherforecast.core.utils.Resource
import com.example.openweatherforecast.db.WeatherResponseEntity
import com.example.openweatherforecast.response.WeatherResponse

interface OpenWeatherMapRepository {
    suspend fun getWeatherForecast(city: String): Resource<WeatherResponseEntity>
    fun getWeatherForecastFromDB(): LiveData<List<WeatherResponseEntity>>
}
