package com.example.mytestapp.presentation.feature.detail

import com.example.mytestapp.data.model.AllDayWeather
import com.example.mytestapp.domain.usecase.LoadAllDayWeatherUseCase
import com.example.mytestapp.presentation.common.SingleLiveEvent
import com.example.mytestapp.presentation.extension.toNetworkException
import com.example.mytestapp.presentation.feature.base.*
import kotlinx.coroutines.flow.catch

class DetailViewModel (private val loadAllDayWeatherUseCase: LoadAllDayWeatherUseCase) : BaseViewModel<Any, ViewEffect>() {
    private val _showAllDayWeatherSuccess = SingleLiveEvent<ViewState<AllDayWeather>>()
    val showAllDayWeatherSuccess: SingleLiveEvent<ViewState<AllDayWeather>>
        get() = _showAllDayWeatherSuccess

    fun loadAllDayWeather(lat:Double,long: Double) = executeUseCase(
        action = {
            loadAllDayWeatherUseCase.execute(lat = lat, long = long)
                .catch { exception ->
                    val networkException = exception.toNetworkException()
                    _showAllDayWeatherSuccess.value = Error(networkException)
                }.collect {
                    _showAllDayWeatherSuccess.value = Success(it)
                }
        }, noInternetAction = {
            _showAllDayWeatherSuccess.value = NoInternetState()
        })

}