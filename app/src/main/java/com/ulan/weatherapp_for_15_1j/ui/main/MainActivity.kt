package com.ulan.weatherapp_for_15_1j.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ulan.weatherapp_for_15_1j.R
import com.ulan.weatherapp_for_15_1j.databinding.ActivityMainBinding
import com.ulan.weatherapp_for_15_1j.ui.loadImage
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this)[WeatherViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.getCurrentWeather()

        viewModel.liveData.observe(this) {
            binding.btnLocation.text = "${it.location.country}, ${it.location.name}"
            binding.mainDegree.text = it.current.tempC.toString()
            binding.txtWeatherSunny.text = it.current.condition.text
            binding.imgWeatherSunny.loadImage("https:${it.current.condition.icon}")
            binding.txt1.text = it.forecast.forecastDay[0].day.maxTempC
            binding.txt2.text = it.forecast.forecastDay[0].day.minTempC
            binding.txtWeatherHumidityIs.text = "${it.current.humidity}%"
            binding.txtWeatherPressureIs.text = "${it.current.pressuremMb}mBar"
            binding.txtWeatherWindIs.text = "${it.current.windKph}km/h"
            binding.txtSunriseIs.text = it.forecast.forecastDay[0].astro.sunrise
            binding.txtSunsetIs.text = it.forecast.forecastDay[0].astro.sunset
            binding.dayDataTimeText.text =
                formatUnixTimestamp(it.location.localtimeEpoch.toLong(), it.location.zoneId)

        }
    }

    private fun formatUnixTimestamp(unixTimestamp: Long, zoneId: String): String {
        val instant = Instant.ofEpochSecond(unixTimestamp)
        val zoneDateTime = instant.atZone(ZoneId.of(zoneId))
        val formatter = DateTimeFormatter.ofPattern("HH'h' mm'm'", Locale.ENGLISH)
        return formatter.format(zoneDateTime)
    }

    private fun startUpdateTime() {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")
        runnable = object : Runnable {
            override fun run() {
                val currentTime = LocalDateTime.now()
                val formattedTime = formatter.format(currentTime)
                updateTime(formattedTime)
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }

    private fun updateTime(time: String) {
        binding.dayDataTimeText.text = time
    }

    private fun stopUpdateTime() {
        handler.removeCallbacks(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        startUpdateTime()
    }

    override fun onPause() {
        super.onPause()
        stopUpdateTime()
    }
}