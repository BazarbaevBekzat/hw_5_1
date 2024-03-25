package com.ulan.weatherapp_for_15_1j.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ulan.weatherapp_for_15_1j.data.model.BaseMainResponse
import com.ulan.weatherapp_for_15_1j.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {

    fun getWeatger(): MutableLiveData<BaseMainResponse> {
        val liveData = MutableLiveData<BaseMainResponse>()
        apiService.getWeather().enqueue(object : Callback<BaseMainResponse>{
            override fun onResponse(
                call: Call<BaseMainResponse>,
                response: Response<BaseMainResponse>
            ) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<BaseMainResponse>, t: Throwable) {
                t.message?.let { Log.e("current_weather", it) }
            }

        })
        return liveData
    }
}