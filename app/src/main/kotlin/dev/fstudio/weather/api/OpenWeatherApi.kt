package dev.fstudio.weather.api

import dev.fstudio.weather.api.get.Forecast
import dev.fstudio.weather.api.get.FullForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Forecast

    @GET("onecall")
    suspend fun getFullForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): FullForecast
}