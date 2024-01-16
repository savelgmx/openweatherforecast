package com.example.openweatherforecast.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "weather")
data class CurrentWeatherEntity @JvmOverloads constructor(
    @Embedded
    val coord: CoordEntity = CoordEntity(0.0, 0.0),
    @TypeConverters(WeatherListConverter::class)
    val weather: List<WeatherEntityItem> = emptyList(),
    val base: String = "",
    @Embedded
    val main: MainEntity = MainEntity(0.0, 0.0, 0.0, 0.0, 0, 0),
    val visibility: Int = 0,
    @Embedded
    val wind: WindEntity = WindEntity(0.0, 0),
    @Embedded
    val clouds: CloudsEntity = CloudsEntity(0),
    val dt: Long = 0L,
    @Embedded(prefix = "sys_")
    val sys: SysEntity = SysEntity(0, 0, 0.0, "", 0L, 0L),
    val timezone: Int = 0,

    val name: String,
    val cod: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

data class CoordEntity(
    val lon: Double,
    val lat: Double
)

data class WeatherEntityItem(
    val weatherId: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MainEntity(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

data class WindEntity(
    val speed: Double,
    val deg: Int
)

data class CloudsEntity(
    val all: Int
)

data class SysEntity(
    val type: Int,
    val id: Int,
    val message: Double,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

class WeatherListConverter {
    @TypeConverter
    fun fromString(value: String): List<WeatherEntityItem> {
        // Implement logic to convert a string to List<WeatherEntityItem>
        // Example: Use a JSON deserializer or any other method based on your data structure
        // For simplicity, let's assume a simple format: weather1|main1|desc1|icon1,weather2|main2|desc2|icon2
        val items = value.split(",")
        return items.map {
            val parts = it.split("|")
            WeatherEntityItem(parts[0].toInt(), parts[1], parts[2], parts[3])
        }
    }

    @TypeConverter
    fun toString(value: List<WeatherEntityItem>): String {
        // Implement logic to convert List<WeatherEntityItem> to a string
        // Example: Use a JSON serializer or any other method based on your data structure
        // For simplicity, let's assume a simple format: weather1|main1|desc1|icon1,weather2|main2|desc2|icon2
        return value.joinToString(",") {
            "${it.weatherId}|${it.main}|${it.description}|${it.icon}"
        }
    }
}
