package com.example.mytestapp.di.feature


import com.example.mytestapp.presentation.feature.main.HomeMainViewModel
import com.example.mytestapp.data.repository.AnimalRepository
import com.example.mytestapp.data.repository.AttendWorkRepositoryImpl
import com.example.mytestapp.domain.usecase.AnimalUseCase
import com.example.mytestapp.domain.usecase.AnimalUseCaseImpl
import com.example.mytestapp.presentation.feature.data_user.DataAnimalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {

    factory<AnimalRepository> {
        AttendWorkRepositoryImpl(
            realm = get()
        )
    }

    factory<AnimalUseCase> {
        AnimalUseCaseImpl(
            animalRepository = get()
        )
    }

    viewModel { DataAnimalViewModel( get()) }


    viewModel { HomeMainViewModel( get(),get()) }
}