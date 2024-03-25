package com.ulan.weatherapp_for_15_1j.data.remote

import com.ulan.weatherapp_for_15_1j.BuildConfig.API_KEY
import com.ulan.weatherapp_for_15_1j.data.model.BaseMainResponse
import com.ulan.weatherapp_for_15_1j.data.model.CurrentDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
    fun getWeather(
        @Query("key") apiKey: String = API_KEY,
        @Query("days") days: Int = 3,
        @Query("q") cityName: String = "Bishkek"
    ): Call<BaseMainResponse>
}