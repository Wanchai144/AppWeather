package com.example.mytestapp.presentation.feature.main

import com.example.mytestapp.domain.usecase.AnimalUseCase
import com.example.mytestapp.presentation.feature.main.base.BaseViewModel
import io.realm.Realm


class HomeMainViewModel(
    private val realm: Realm,
    private val attendWorkUseCase: AnimalUseCase
): BaseViewModel() {}