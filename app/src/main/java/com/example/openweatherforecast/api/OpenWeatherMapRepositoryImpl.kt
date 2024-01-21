package com.example.openweatherforecast.api


import androidx.lifecycle.LiveData
import com.example.openweatherforecast.core.utils.Resource
import com.example.openweatherforecast.db.CloudsEntity
import com.example.openweatherforecast.db.CoordEntity
import com.example.openweatherforecast.db.CurrentWeatherEntity
import com.example.openweatherforecast.db.MainEntity
import com.example.openweatherforecast.db.OpenWeatherMapDao
import com.example.openweatherforecast.db.SysEntity
import com.example.openweatherforecast.db.WeatherEntityItem
import com.example.openweatherforecast.db.WindEntity
import com.example.openweatherforecast.di.NetworkObject
import com.example.openweatherforecast.response.WeatherResponse
import javax.inject.Inject



class OpenWeatherMapRepositoryImpl @Inject constructor(
    private val api: OpenWeatherMapAPI,
    private val dao: OpenWeatherMapDao
) : OpenWeatherMapRepository {

    override suspend fun getWeatherForecast(city: String): Resource<WeatherResponse> {
        val response = api.getCurrentWeather(city, NetworkObject.API_KEY)
        return if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                Resource.Success(data)
            } else {
                Resource.Error(null, "Empty response body")
            }
        } else {
            Resource.Error(null, "No data found")
        }
    }

    override suspend fun getWeatherForecastFromDB(): LiveData<List<CurrentWeatherEntity>> {
        TODO("Not yet implemented")
    }
}

