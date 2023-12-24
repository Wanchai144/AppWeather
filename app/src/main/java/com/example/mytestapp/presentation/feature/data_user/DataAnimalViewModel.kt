package com.example.mytestapp.presentation.feature.data_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.data.model.UserEntity
import com.example.mytestapp.domain.usecase.AnimalUseCase
import com.example.mytestapp.presentation.feature.main.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DataAnimalViewModel( private val animalUseCase: AnimalUseCase) : BaseViewModel() {

    private val _showList = MutableLiveData<List<UserEntity>>()
    val showList: LiveData<List<UserEntity>> = _showList

    private val onSuccess = MutableLiveData<Unit>()

    init {
        saveUser()
        getUser()
    }


    private fun getUser() = viewModelScope.launch {
        animalUseCase.executeGetData().collect {
            _showList.value = it
        }
    }



    private fun saveUser() = viewModelScope.launch {
        animalUseCase.executeSaveData()
            .onStart {}
            .catch { exception ->
                exception.printStackTrace()
            }.onCompletion {}
            .collect { data ->
                onSuccess.value = data
            }
        }


}