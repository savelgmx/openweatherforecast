package com.example.openweatherforecast.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface OpenWeatherMapDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(currentWeatherEntity: CurrentWeatherEntity)
    @Query("SELECT * FROM weather")
    fun getWeather(): LiveData<CurrentWeatherEntity?>

}

