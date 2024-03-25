package com.ulan.weatherapp_for_15_1j.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ulan.weatherapp_for_15_1j.data.model.BaseMainResponse
import com.ulan.weatherapp_for_15_1j.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    var liveData = MutableLiveData<BaseMainResponse>()

    fun getCurrentWeather() {
        liveData = repository.getWeatger()
    }
}