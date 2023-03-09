package com.example.mytestapp.data.source.remote.api.service



import com.example.mytestapp.BuildConfig
import com.example.mytestapp.data.source.remote.api.response.WeatherHourlyResponse
import com.example.mytestapp.data.source.remote.api.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("weather?")
    suspend fun getWeatherDetail(
        @Query("q") city: String,
        @Query("appid") apiKey: String =  BuildConfig.API_KEY_ID
    ): Response<WeatherResponse>


    @GET("onecall?")
    suspend fun getWeatherHourly(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = BuildConfig.API_KEY_ID
    ): Response<WeatherHourlyResponse>
}