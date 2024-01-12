package com.example.openweatherforecast.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.openweatherforecast.response.WeatherResponse


@Database(entities = [CurrentWeatherEntity::class], version = 1, exportSchema = false)
abstract class OpenWeatherMapDatabase : RoomDatabase() {
    abstract fun openWeatherMapDao(): OpenWeatherMapDao

    companion object {
        @Volatile
        private var instance: OpenWeatherMapDatabase? = null
        fun getInstance(context: Context): OpenWeatherMapDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): OpenWeatherMapDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                OpenWeatherMapDatabase::class.java,
                "open_weather_map_database"
            ).build()
        }
    }
}

