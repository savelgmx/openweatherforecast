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

