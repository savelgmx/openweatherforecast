package com.example.openweatherforecast.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.openweatherforecast.api.OpenWeatherMapRepository
import com.example.openweatherforecast.core.utils.Resource
import com.example.openweatherforecast.db.CurrentWeatherEntity
import com.example.openweatherforecast.response.Clouds
import com.example.openweatherforecast.response.Coord
import com.example.openweatherforecast.response.Main
import com.example.openweatherforecast.response.Sys
import com.example.openweatherforecast.response.Weather
import com.example.openweatherforecast.response.WeatherResponse
import com.example.openweatherforecast.response.Wind
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope

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
            base = firstEntity.base ?: "",
            clouds = Clouds(all = firstEntity.clouds?.all ?: 0),
            cod = firstEntity.cod ?: 0,
            coord = Coord(
                lat = firstEntity.coord?.lat ?: 0.0,
                lon = firstEntity.coord?.lon ?: 0.0
            ),
            dt = firstEntity.dt ?: 0,
            id = firstEntity.id ?: 0,
            main = Main(
                temp = firstEntity.main?.temp ?: 0.0,
                pressure = firstEntity.main?.pressure ?: 0,
                humidity = firstEntity.main?.humidity ?: 0,
                temp_min = firstEntity.main?.temp_min ?: 0.0,
                temp_max = firstEntity.main?.temp_max ?: 0.0,
                feels_like = firstEntity.main?.feels_like ?:0.0
            ),
            name = firstEntity.name ?: "",
            sys = Sys(
                country = firstEntity.sys?.country ?: "",
                sunrise = firstEntity.sys?.sunrise ?: 0,
                sunset = firstEntity.sys?.sunset ?: 0,
                id = firstEntity.sys?.id?:0,
                message = firstEntity.sys?.message?:0.0,
                type = firstEntity.sys?.type?:0

            ),
            timezone = firstEntity.timezone ?: 0,
            visibility = firstEntity.visibility ?: 0,
            weather = firstEntity.weather?.map {
                Weather(
                    description = it?.description ?: "",
                    icon = it?.icon ?: "",
                    id = it?.weatherId?: 0,
                    main = it?.main ?: ""
                )
            } ?: listOf(),
            wind = Wind(
                speed = firstEntity.wind?.speed ?: 0.0,
                deg = firstEntity.wind?.deg ?: 0
            )
            )
        } else {
            // Return a default WeatherResponse or throw an exception based on your requirements
        WeatherResponse(
            base = "",
            clouds = Clouds( 0),
            cod = 0,
            coord = Coord(0.0,0.0),
            dt = 0,
            id = 0,
            main = Main(0.0,0.0,0.0,0.0,0,0),
            name = "",
            sys = Sys(0,0,0.0,"", 0L, 0L),
            timezone = 0,
            visibility = 0,
            weather = listOf(),
            wind = Wind(0.0,0)
        )
        }
    }

    // Conversion function for a single CurrentWeatherEntity to Weather
    private fun convertEntityToWeather(entity: CurrentWeatherEntity): Weather {
        return Weather(
            description = entity.weather[0].description,
            icon = entity.weather[0].icon,
            id = entity.id,
            main = entity.weather[0].main
        )
    }
}
