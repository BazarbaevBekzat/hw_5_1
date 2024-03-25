package com.ulan.weatherapp_for_15_1j.data.model

import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("maxtemp_c")
    val maxTempC: String,
    @SerializedName("mintemp_c")
    val minTempC: String,
    @SerializedName("maxwind_kph")
    val maxWindKph: String,
    val condition: ConditionDto
)