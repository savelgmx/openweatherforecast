package com.example.openweatherforecast.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.openweatherforecast.api.OpenWeatherMapRepository
import com.example.openweatherforecast.core.utils.Resource
import com.example.openweatherforecast.db.CurrentWeatherEntity
import com.example.openweatherforecast.response.Clouds
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
                        emitSource(repository.getWeatherForecastFromDB().map { entities ->
                            if (entities.isNullOrEmpty()) {
                                Resource.Error<WeatherResponse>(null, "Database is empty")
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

    // Conversion function for List<CurrentWeatherEntity> to WeatherResponse
    private fun convertEntitiesToWeatherResponse(entities: List<CurrentWeatherEntity>): WeatherResponse {
        val firstEntity = entities.firstOrNull()
        return if (firstEntity != null) {
            WeatherResponse(
                base = firstEntity.base,

                clouds = Clouds(), // You need to provide Clouds data
                cod = firstEntity.cod,
                coord = Coord(), // You need to provide Coord data
                dt = firstEntity.dt,
                id = firstEntity.id,
                main = Main(), // You need to provide Main data
                name = firstEntity.name,
                sys = Sys(), // You need to provide Sys data
                timezone = firstEntity.timezone,
                visibility = firstEntity.visibility,
                weather = listOf(), // You need to provide Weather data
                wind = Wind() // You need to provide Wind data
            )
        } else {
            // Return a default WeatherResponse or throw an exception based on your requirements
            WeatherResponse()
        }
    }

    // Conversion function for a single CurrentWeatherEntity to Weather
    private fun convertEntityToWeather(entity: CurrentWeatherEntity): Weather {
        return Weather(
            description = entity.description,
            icon = entity.icon,
            id = entity.id,
            main = entity.main
        )
    }
}
