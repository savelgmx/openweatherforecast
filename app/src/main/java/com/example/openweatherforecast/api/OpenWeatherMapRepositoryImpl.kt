package com.example.openweatherforecast.api

import OpenWeatherMapAPI
import androidx.lifecycle.LiveData
import javax.inject.Inject

class OpenWeatherMapRepositoryImpl @Inject constructor(private val api: OpenWeatherMapAPI, private val dao: OpenWeatherMapDao) : OpenWeatherMapRepository {

    override suspend fun getWeatherForecast(city: String): Resource<OpenWeatherMapResponse> {
        val response = api.getWeatherForecast(city, OPEN_WEATHER_MAP_API_KEY)
        return if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                dao.insert(data)
                Resource.Success(data)
            } else {
                Resource.Error("No data found")
            }
        } else {
            val message = response.message()
            Resource.Error(message)
        }
    }

    override fun getWeatherForecastFromDB(): LiveData<List<OpenWeatherMapResponse>> {
        return dao.getWeatherForecast()
    }

}
