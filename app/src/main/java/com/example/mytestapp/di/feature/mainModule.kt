package com.example.mytestapp.di.feature

import com.example.mytestapp.data.repository.WeatherRepository
import com.example.mytestapp.data.repository.WeatherRepositoryImpl
import com.example.mytestapp.domain.usecase.GetDataUseCase
import com.example.mytestapp.domain.usecase.GetDataUseCaseImpl
import com.example.mytestapp.domain.usecase.LoadAllDayWeatherUseCase
import com.example.mytestapp.domain.usecase.LoadAllDayWeatherUseCaseImpl
import com.example.mytestapp.presentation.feature.detail.DetailViewModel
import com.example.mytestapp.presentation.feature.home.HomeViewModel
import com.example.mytestapp.presentation.feature.main.HomeMainViewModel
import com.example.mytestapp.presentation.feature.viewmodel.share.ShareMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {
    viewModel {
        HomeMainViewModel()
    }

    viewModel {
        HomeViewModel(getDataUseCase = get())
    }

    factory<GetDataUseCase> {
        GetDataUseCaseImpl(
           weatherRepository = get()
        )
    }

    factory<WeatherRepository> {
        WeatherRepositoryImpl(apiService = get(), gson = get())
    }

    viewModel {
        DetailViewModel(loadAllDayWeatherUseCase = get())
    }

    factory<LoadAllDayWeatherUseCase> {
        LoadAllDayWeatherUseCaseImpl(
            weatherRepository = get()
        )
    }

    viewModel {
        ShareMainViewModel()
    }


}