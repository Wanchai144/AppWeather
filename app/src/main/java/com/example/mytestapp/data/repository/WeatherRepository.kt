package com.example.mytestapp.data.repository

import com.example.mytestapp.data.source.remote.api.response.WeatherHourlyResponse
import com.example.mytestapp.data.source.remote.api.response.WeatherResponse
import com.example.mytestapp.data.source.remote.api.service.APIService
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext



interface WeatherRepository {
    fun weather(city: String): Flow<WeatherResponse>
    fun allDayWeather(lat: Double,long: Double): Flow<WeatherHourlyResponse>
}

class WeatherRepositoryImpl(
    private val apiService: APIService,
    private val gson: Gson
) : WeatherRepository {

    override fun weather(city:String): Flow<WeatherResponse> = flow {
        val response = apiService.getWeatherDetail(city)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(body)
            } else {
                throw HttpException(response)
            }
        } else {
            throw HttpException(response)
        }
    }

    override fun allDayWeather(lat:Double,long:Double): Flow<WeatherHourlyResponse> = flow {
        val response = apiService.getWeatherHourly(lat = lat, lon = long)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(body)
            } else {
                throw HttpException(response)
            }
        } else {
            throw HttpException(response)
        }
    }
}