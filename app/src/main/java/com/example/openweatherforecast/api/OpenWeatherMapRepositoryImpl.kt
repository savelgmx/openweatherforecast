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
        return Resource.Success(response.body()) /*if (response.isSuccessful) {
            val data = response.body()
            Resource.Success(data)

            } else {
                Resource.Error(null, "No data found")
            }
        }*/

    }

    override suspend fun getWeatherForecastFromDB(): LiveData<List<CurrentWeatherEntity>> {
    TODO("Not yet implemented")
}

fun convertToWeatherEntity(weatherResponse: WeatherResponse): CurrentWeatherEntity {
        val coordEntity = CoordEntity(weatherResponse.coord.lon, weatherResponse.coord.lat)

        val weatherEntityItems = weatherResponse.weather.map {
            WeatherEntityItem(it.id, it.main, it.description, it.icon)
        }

        val mainEntity = MainEntity(
            weatherResponse.main.temp,
            weatherResponse.main.feels_like,
            weatherResponse.main.temp_min,
            weatherResponse.main.temp_max,
            weatherResponse.main.pressure,
            weatherResponse.main.humidity
        )

        val windEntity = WindEntity(weatherResponse.wind.speed, weatherResponse.wind.deg)
        val cloudsEntity = CloudsEntity(weatherResponse.clouds.all)

        val sysEntity = SysEntity(
            weatherResponse.sys.type,
            weatherResponse.sys.id,
            weatherResponse.sys.message,
            weatherResponse.sys.country,
            weatherResponse.sys.sunrise,
            weatherResponse.sys.sunset
        )

        return CurrentWeatherEntity(
            coordEntity,
            weatherEntityItems,
            weatherResponse.base,
            mainEntity,
            weatherResponse.visibility,
            windEntity,
            cloudsEntity,
            weatherResponse.dt,
            sysEntity,
            weatherResponse.timezone,
            weatherResponse.id,
            weatherResponse.name,
            weatherResponse.cod
        )
    }
}
