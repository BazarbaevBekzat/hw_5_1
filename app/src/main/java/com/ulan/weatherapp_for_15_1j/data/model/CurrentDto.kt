package com.ulan.weatherapp_for_15_1j.data.model

import com.google.gson.annotations.SerializedName

data class CurrentDto(
    val condition: ConditionDto,
    val humidity: Int,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("pressure_mb")
    val pressuremMb: Double,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("wind_degree")
    val windDegree: Int,
    @SerializedName("wind_kph")
    val windKph: Double,
)
