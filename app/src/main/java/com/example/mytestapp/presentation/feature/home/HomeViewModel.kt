package com.example.mytestapp.presentation.feature.home

import android.util.Log
import com.example.mytestapp.data.model.Coin
import com.example.mytestapp.domain.usecase.GetDataUseCase
import com.example.mytestapp.presentation.common.SingleLiveEvent
import com.example.mytestapp.presentation.extension.toNetworkException
import com.example.mytestapp.presentation.feature.base.*
import kotlinx.coroutines.flow.catch

class HomeViewModel(private val getDataUseCase: GetDataUseCase) : BaseViewModel<Any, ViewEffect>() {

    private val _showDataSuccess = SingleLiveEvent<ViewState<Coin>>()
    val showDataSuccess: SingleLiveEvent<ViewState<Coin>>
        get() = _showDataSuccess

    fun loadData() = executeUseCase(
        action = {
            getDataUseCase.execute()
                .catch { exception ->
                    val networkException = exception.toNetworkException()
                    _showDataSuccess.value = Error(networkException)
                }.collect {
                    _showDataSuccess.value = Success(it)
                }
        }, noInternetAction = {
            _showDataSuccess.value = NoInternetState()
        })


    fun saveDataCoin(coin: Coin) = executeUseCase(
        action = {
            getDataUseCase.saveData(coin)
                .catch { exception ->
                    val networkException = exception.toNetworkException()
                    Log.d("TAGAA", "$networkException")
                }.collect {
                    Log.d("TAGAA", "saveDataCoin: ")
                }
        }, noInternetAction = {
            _showDataSuccess.value = NoInternetState()
        })
}