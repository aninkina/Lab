package dev.fstudio.weather.api.get.model

data class SimpleWeather(
    val date: String,
    val temp: Double,
    val feelsLike: Double
)