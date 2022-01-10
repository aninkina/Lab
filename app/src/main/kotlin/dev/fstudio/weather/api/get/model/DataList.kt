package dev.fstudio.weather.api.get.model

import kotlinx.serialization.Serializable
@Serializable
data class DataList(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain? = null,
    val snow: Snow? = null,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)