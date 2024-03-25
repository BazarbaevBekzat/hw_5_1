package com.ulan.weatherapp_for_15_1j.di

import com.ulan.weatherapp_for_15_1j.BuildConfig.BASE_URL
import com.ulan.weatherapp_for_15_1j.data.remote.ApiService
import com.ulan.weatherapp_for_15_1j.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatgerModule {

    @Provides
    @Singleton
    fun provideRetrofit() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    fun provideWeatherRepository(apiService: ApiService) =
        WeatherRepository(apiService)
}