package com.example.mytestapp.domain.usecase

import com.example.mytestapp.data.model.AllDayWeather
import com.example.mytestapp.data.model.CustomHourly
import com.example.mytestapp.data.repository.WeatherRepository
import com.example.mytestapp.data.source.remote.api.response.WeatherHourlyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface LoadAllDayWeatherUseCase {
    fun execute(lat:Double,long: Double): Flow<AllDayWeather>
}

class LoadAllDayWeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : LoadAllDayWeatherUseCase {
    override fun execute(lat:Double,long: Double): Flow<AllDayWeather> = flow {
        weatherRepository.allDayWeather(lat = lat, long = long).collect { data ->
            val allDayWeather = mapDataAllDayWeatherModel(data)
            emit(allDayWeather)
        }
    }

    private fun mapDataAllDayWeatherModel(weather: WeatherHourlyResponse): AllDayWeather {
        weather.let { res ->
            val weather: AllDayWeather = AllDayWeather().apply {
                val weather_hourly: ArrayList<CustomHourly> = arrayListOf()
                res.hourly?.let {
                    res.current?.let { current ->
                        res.hourly.forEach {  hourly ->
                            temp = current.temp
                            feels_like = current.feels_like
                            humidity = current.humidity
                            dt = current.dt
                            country =res.timezone
                            weather_hourly.add(CustomHourly(dt_hourly = hourly.dt,feels_like_hourly =hourly.feels_like,humidity_hourly = hourly.humidity,temp_hourly = hourly.temp ))
                        }

                    }
                    _hourly = weather_hourly
                }
            }
            return weather
        }
    }
}
