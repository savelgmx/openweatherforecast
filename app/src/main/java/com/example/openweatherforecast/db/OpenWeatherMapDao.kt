package com.example.openweatherforecast.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.openweatherforecast.response.WeatherResponse

import javax.inject.Singleton


@Singleton
@Dao
interface OpenWeatherMapDao {
    @Query("SELECT * FROM weather_response")
    fun getWeatherForecast(): LiveData<List<WeatherResponseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: WeatherResponseEntity)
}
