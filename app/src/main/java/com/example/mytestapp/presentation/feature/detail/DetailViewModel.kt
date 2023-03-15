package com.example.mytestapp.presentation.feature.detail


import com.example.mytestapp.data.model.Coin
import com.example.mytestapp.data.model.SectionModel
import com.example.mytestapp.domain.usecase.GetDataUseCase
import com.example.mytestapp.domain.usecase.LoadDataRetrospectUseCase
import com.example.mytestapp.presentation.common.SingleLiveEvent
import com.example.mytestapp.presentation.extension.toNetworkException
import com.example.mytestapp.presentation.feature.base.*
import kotlinx.coroutines.flow.catch

class DetailViewModel (private  val case: LoadDataRetrospectUseCase) : BaseViewModel<Any, ViewEffect>() {
    private val _showRetrospectSuccess = SingleLiveEvent<ViewState<List<Coin>>>()
    val showRetrospectSuccess: SingleLiveEvent<ViewState<List<Coin>>>
        get() = _showRetrospectSuccess


    fun loadDataRetrospect() = executeUseCase(
        action = {
            case.execute()
                .catch { exception ->
                    val networkException = exception.toNetworkException()
                    _showRetrospectSuccess.value = Error(networkException)
                }.collect {
                    _showRetrospectSuccess.value = Success(it)
                }
        }, noInternetAction = {
            _showRetrospectSuccess.value = NoInternetState()
        })

}