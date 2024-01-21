package com.example.openweatherforecast.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.openweatherforecast.api.OpenWeatherMapRepository
import com.example.openweatherforecast.core.utils.Resource
import com.example.openweatherforecast.db.CurrentWeatherEntity
import com.example.openweatherforecast.response.Weather
import com.example.openweatherforecast.response.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.flow.internal.NopCollector.emit


import javax.inject.Inject

@HiltViewModel
class OpenWeatherMapViewModel @Inject constructor(
    private val repository: OpenWeatherMapRepository) : ViewModel() {

    suspend fun getWeatherForecast(city: String): LiveData<Resource<WeatherResponse>> {
        return liveData {
            coroutineScope {
                emit(Resource.Loading())
                when (val status = repository.getWeatherForecast(city)) {
                    is Resource.Success -> Resource.Success(status.data)?.let { emit(it) }
                    is Resource.Error -> {
                        emit(Resource.Error(status.data, status.msg))
                        // Modify the line below to handle the conversion from database entity to WeatherResponse
                        emitSource(repository.getWeatherForecastFromDB().map { entities ->
                            if (entities.isNullOrEmpty()) {
                                Resource.Error<List<CurrentWeatherEntity>>(null, "Database is empty")
                            } else {
                                val weatherResponse = convertEntitiesToWeatherResponse(entities)
                                Resource.Success(weatherResponse)
                    }
                        })
                    }
                    else -> {}
                }
            }
        }
    }

    // Add a function to convert List<CurrentWeatherEntity> to WeatherResponse
    private fun convertEntitiesToWeatherResponse(entities: List<CurrentWeatherEntity>): WeatherResponse {
        // Implement the logic to convert entities to WeatherResponse
        // This might involve mapping fields and creating a new WeatherResponse object
        // For demonstration, let's assume there is a function named convertEntityToWeather in your repository
        return WeatherResponse(convertEntityToWeather(entities.first()))
    }

    private fun convertEntityToWeather(entity: CurrentWeatherEntity): Weather {
        // Implement the logic to convert a single entity to Weather
        // This might involve mapping fields and creating a new Weather object
        return Weather(/* provide necessary data */)
    }
}
