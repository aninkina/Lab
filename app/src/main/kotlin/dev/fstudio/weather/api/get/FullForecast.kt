package dev.fstudio.weather.api.get

import dev.fstudio.weather.api.get.model_onecall.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FullForecast(
    val alerts: List<Alert>? = null,
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>? = null,
    val timezone: String,
    @SerialName("timezone_offset")
    val timezoneOffset: Int
)