package com.ulan.weatherapp_for_15_1j.data.model

data class BaseMainResponse(
    val location: LocationDto,
    val current: CurrentDto,
    val forecast: ForecastDto

)
