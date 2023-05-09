package com.example.openweatherforecast.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.openweatherforecast.response.WeatherResponse


@Database(entities = [WeatherResponse::class], version = 1, exportSchema = false)
abstract class OpenWeatherMapDatabase : RoomDatabase() {
    abstract fun openWeatherMapDao(): OpenWeatherMapDao

    companion object {
        @Volatile
        private var INSTANCE: OpenWeatherMapDatabase? = null

        fun getDatabase(context: Context): OpenWeatherMapDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OpenWeatherMapDatabase::class.java,
                    "open_weather_map_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}