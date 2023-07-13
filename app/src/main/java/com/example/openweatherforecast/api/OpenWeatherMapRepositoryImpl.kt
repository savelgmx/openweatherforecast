package com.example.openweatherforecast.api


import androidx.lifecycle.LiveData
import com.example.openweatherforecast.core.utils.Resource
import com.example.openweatherforecast.db.OpenWeatherMapDao
import com.example.openweatherforecast.db.WeatherResponseEntity
import com.example.openweatherforecast.di.NetworkObject
import com.example.openweatherforecast.response.WeatherResponse
import com.google.gson.Gson
import javax.inject.Inject



class OpenWeatherMapRepositoryImpl @Inject constructor(
    private val api: OpenWeatherMapAPI,
    private val dao: OpenWeatherMapDao
) : OpenWeatherMapRepository {

    override suspend fun getWeatherForecast(city: String): Resource<WeatherResponseEntity> {
        val response = api.getWeatherForecast(city, NetworkObject.API_KEY)
        return if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                val weatherResponseEntity = mapToWeatherResponseEntity(data)
                dao.insertWeatherResponse(weatherResponseEntity)
                Resource.Success(weatherResponseEntity)
            } else {
                Resource.Error(null, "No data found")
            }
        } else {
            val data = response.body()
            Resource.Error(data, "Something went wrong")
        }
    }

    override fun getWeatherForecastFromDB(): LiveData<List<WeatherResponseEntity>> {
        return dao.getWeatherForecast()
    }

    private fun mapToWeatherResponseEntity(weatherResponse: WeatherResponse): WeatherResponseEntity {
        val weatherResponseJson = Gson().toJson(weatherResponse)
        return WeatherResponseEntity(weatherResponseJson)
    }
}
