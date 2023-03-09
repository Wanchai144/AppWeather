package com.example.mytestapp.domain.usecase

import com.example.mytestapp.data.repository.WeatherRepository
import com.example.mytestapp.data.source.remote.api.response.WeatherResponse
import com.example.mytestapp.data.model.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetDataUseCase {
    fun execute(city: String): Flow<Weather>
}

class GetDataUseCaseImpl(private val weatherRepository: WeatherRepository) : GetDataUseCase {
    override fun execute(city: String): Flow<Weather> = flow {
        weatherRepository.weather(city).collect { data ->
            val weatherModel = mapDataWeatherModel(data)
            emit(weatherModel)
        }
    }


    private fun mapDataWeatherModel(weather: WeatherResponse): Weather {
        weather.let { res ->
            val weathers: Weather = Weather().apply {
                res.main?.let { main ->
                    res.sys?.let { sys ->
                        temp = main.temp
                        feels_like = main.feels_like
                        temp_min = main.temp_min
                        temp_max = main.temp_max
                        humidity = main.humidity
                        country = res.name
                        date_time = res.dt
                    }
                }

            }
            return weathers
        }
    }
}

