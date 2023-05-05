package com.example.openweatherforecast.api

import OpenWeatherMapAPI
import androidx.lifecycle.LiveData
import com.example.openweatherforecast.core.utils.Resource
import com.example.openweatherforecast.db.OpenWeatherMapDao
import com.example.openweatherforecast.di.NetworkObject
import com.example.openweatherforecast.response.WeatherResponse
import javax.inject.Inject

class OpenWeatherMapRepositoryImpl @Inject constructor(private val api: OpenWeatherMapAPI, private val dao: OpenWeatherMapDao) : OpenWeatherMapRepository {

    override suspend fun getWeatherForecast(city: String): Resource<WeatherResponse> {
        val response = api.getWeatherForecast(city, NetworkObject.API_KEY)
        return if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                dao.insert(data)
                Resource.Success(data)
            } else {
                Resource.Error(data,"No data found")
            }
        } else {
            val message = response.message()
            Resource.Error(message)
        }
    }

    override fun getWeatherForecastFromDB(): LiveData<List<WeatherResponse>> {
        return dao.getWeatherForecast()
    }

}
