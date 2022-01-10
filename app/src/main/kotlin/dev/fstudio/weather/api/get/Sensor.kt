package dev.fstudio.weather.api.get

import kotlinx.serialization.Serializable

@Serializable
data class Sensor(
    val name: String,
    val serial_number: String,
    val place: String,
    val status: Boolean,
    val owner: String,
)