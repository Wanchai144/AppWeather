package com.example.mytestapp.presentation.feature.home

import androidx.lifecycle.MutableLiveData
import com.example.mytestapp.data.model.Weather
import com.example.mytestapp.domain.usecase.GetDataUseCase
import com.example.mytestapp.presentation.common.SingleLiveEvent
import com.example.mytestapp.presentation.extension.toNetworkException
import com.example.mytestapp.presentation.feature.base.*
import com.example.mytestapp.utils.TextHelper
import com.example.mytestapp.utils.TextWatcherAdapter
import kotlinx.coroutines.flow.catch

class HomeViewModel(private val getDataUseCase: GetDataUseCase) : BaseViewModel<Any, ViewEffect>() {

    private val _showDataSuccess = SingleLiveEvent<ViewState<Weather>>()
    val showDataSuccess: SingleLiveEvent<ViewState<Weather>>
        get() = _showDataSuccess

    val edtCity = MutableLiveData<String?>()

    val onCityTextChanged = TextWatcherAdapter { s ->
        edtCity.value = s
    }

    fun onClickSubmit() {
        if (TextHelper.isNotEmptyStrings(edtCity.value.toString())) loadData(edtCity.value.toString())
    }

    private fun loadData(city: String) = executeUseCase(
        action = {
            getDataUseCase.execute(city)
                .catch { exception ->
                    val networkException = exception.toNetworkException()
                    _showDataSuccess.value = Error(networkException)
                }.collect {
                    _showDataSuccess.value = Success(it)
                }
        }, noInternetAction = {
            _showDataSuccess.value = NoInternetState()
        })
}