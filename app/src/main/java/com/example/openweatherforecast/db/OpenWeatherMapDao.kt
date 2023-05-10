package com.example.openweatherforecast.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.openweatherforecast.response.WeatherResponse
import retrofit2.http.Query

@Dao
interface OpenWeatherMapDao {
    @Query("SELECT * FROM weather_response")
    fun getWeatherForecast(): LiveData<List<WeatherResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: WeatherResponse)
}
