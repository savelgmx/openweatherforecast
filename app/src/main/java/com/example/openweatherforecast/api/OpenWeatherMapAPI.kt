package com.example.openweatherforecast.api

import com.example.openweatherforecast.di.NetworkObject

import com.example.openweatherforecast.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapAPI {

  @GET("weather")
  suspend fun getWeatherForecast(
    @Query("q") cityName: String,
    @Query("units") units: String = "metric",
    @Query("APPID") appId: String = NetworkObject.API_KEY,
  ): Response<WeatherResponse>

  @GET("weather")
  suspend fun getWeatherLatLng(
    @Query("lat") lat: Double,
    @Query("lon") lon: Double,
    @Query("units") units: String = "metric",
    @Query("APPID") appId: String = NetworkObject.API_KEY,
  ): Response<WeatherResponse>
}

/*
interface OpenWeatherMapAPI {

  @GET("weather")
  suspend fun getWeatherOfCity(
    @Query("q") q: String,
    @Query("units") units: String = AppConstants.WEATHER_UNIT,
    @Query("appid") appid: String = AppConstants.WEATHER_API_KEY
  ): Response<WeatherCityResponse>

  @GET("weather")
  suspend fun getWeatherOfLatLon(
    @Query("lat") latitude: String,
    @Query("lon") longitude: String,
    @Query("units") units: String = AppConstants.WEATHER_UNIT,
    @Query("appid") appid: String = AppConstants.WEATHER_API_KEY
  ): Response<WeatherCityResponse>

  @GET("forecast")
  suspend fun findCityForecastData(
    @Query("q") q: String,
    @Query("units") units: String = AppConstants.WEATHER_UNIT,
    @Query("appid") appid: String = AppConstants.WEATHER_API_KEY
  ): Response<ForecastCityResponse>

}*/
