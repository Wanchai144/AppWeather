package com.example.mytestapp.di.feature


import HomeMainViewModel
import com.example.mytestapp.data.repository.AttendWorkRepository
import com.example.mytestapp.data.repository.AttendWorkRepositoryImpl
import com.example.mytestapp.domain.usecase.AttendWorkUseCase
import com.example.mytestapp.domain.usecase.AttendWorkUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {

    factory<AttendWorkRepository> {
        AttendWorkRepositoryImpl(
            realm = get()
        )
    }

    factory<AttendWorkUseCase> {
        AttendWorkUseCaseImpl(
            attendWorkRepository = get()
        )
    }

    viewModel { HomeMainViewModel( get(),get()) }
}