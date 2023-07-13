package com.example.openweatherforecast.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.openweatherforecast.response.WeatherResponse

import javax.inject.Singleton


@Dao
interface OpenWeatherMapDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherResponse(weatherResponse: WeatherResponseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: List<WeatherEntity>)

    @Query("SELECT * FROM weather_response WHERE id = :id")
    fun getWeatherResponseById(id: Int): LiveData<WeatherResponseEntity?>



    @Transaction
    @Query("SELECT * FROM weather_response")
    fun getWeatherResponsesWithWeather(): LiveData<List<WeatherResponseEntityWithWeather>>

    @Query("SELECT * FROM weather_response")
    fun getWeatherForecast(id: Int): LiveData<WeatherResponseEntity>

}
