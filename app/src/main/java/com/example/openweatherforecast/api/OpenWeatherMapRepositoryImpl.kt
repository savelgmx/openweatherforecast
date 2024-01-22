package com.example.openweatherforecast.api


import androidx.lifecycle.LiveData
import com.example.openweatherforecast.core.utils.Resource
import com.example.openweatherforecast.db.CurrentWeatherEntity
import com.example.openweatherforecast.db.OpenWeatherMapDao
import com.example.openweatherforecast.di.NetworkObject
import com.example.openweatherforecast.response.WeatherResponse
import retrofit2.Response
import javax.inject.Inject



class OpenWeatherMapRepositoryImpl @Inject constructor(
    private val openWeatherMapAPI: OpenWeatherMapAPI,
    private val openWeatherMapDao: OpenWeatherMapDao
) : OpenWeatherMapRepository {

    override suspend fun getWeatherForecast(city: String): Resource<WeatherResponse> {
        val response = openWeatherMapAPI.getCurrentWeather(city, NetworkObject.API_KEY)
        return if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                Resource.Success(data)
             //  addForecastDataToDB(data)
            } else {
                Resource.Error(null, "Empty response body")
            }
        } else {
            Resource.Error(null, "No data found")
        }
    }

    override suspend fun getWeatherForecastFromDB(): LiveData<List<CurrentWeatherEntity>> {

        return openWeatherMapDao.getWeather()
    }
    private fun addForecastDataToDB(data:Resource<WeatherResponse>){
        TODO("Not yet implemented")
    }
}

