package com.ulan.weatherapp_for_15_1j.data.model

import com.google.gson.annotations.SerializedName

data class ForecastDayDto(
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: String,
    val day: DayDto,
    val astro: AstroDto
)
