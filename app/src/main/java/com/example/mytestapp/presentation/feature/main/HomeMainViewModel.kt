package com.example.mytestapp.presentation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.presentation.common.Event
import com.example.mytestapp.presentation.common.SingleLiveEvent
import com.example.mytestapp.presentation.feature.base.BaseViewModel
import com.example.mytestapp.presentation.feature.base.ViewEffect
import com.example.mytestapp.presentation.feature.main.winget.BottomMenu


class HomeMainViewModel () : BaseViewModel<Any, ViewEffect>() {

    private var _buttonMenuClick: MutableLiveData<Event<BottomMenu.BottomMenuHomePages>> =
        MutableLiveData()
    val buttonMenuClick: LiveData<Event<BottomMenu.BottomMenuHomePages>>
        get() = _buttonMenuClick


    init {

    }

    fun onBottomMenuClick(page: BottomMenu.BottomMenuHomePages) {
        _buttonMenuClick.value = Event(page)
    }

    fun loadData() = executeUseCase(
        action = {
//            getDataUseCase.execute()
//                .catch { exception ->
//                    val networkException = exception.toNetworkException()
////                    _showLoginSuccess.value = Error(networkException)
//                }.collect {
////                    _showLoginSuccess.value = Success(it!!)
//                }
        }, noInternetAction = {
//            _showLoginSuccess.value = NoInternetState()
        })


}