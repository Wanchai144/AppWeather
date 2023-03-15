package com.example.mytestapp.di.feature

import com.example.mytestapp.data.repository.CoinDeskRepository
import com.example.mytestapp.data.repository.CoinDeskRepositoryImpl
import com.example.mytestapp.domain.usecase.GetDataUseCase
import com.example.mytestapp.domain.usecase.GetDataUseCaseImpl
import com.example.mytestapp.domain.usecase.LoadDataRetrospectUseCase
import com.example.mytestapp.domain.usecase.LoadDataRetrospectUseCaseImpl
import com.example.mytestapp.presentation.feature.detail.DetailViewModel
import com.example.mytestapp.presentation.feature.home.HomeViewModel
import com.example.mytestapp.presentation.feature.main.HomeMainViewModel
import com.example.mytestapp.presentation.feature.point.ScorePointViewModel
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

    viewModel {
        ScorePointViewModel()
    }


    factory<GetDataUseCase> {
        GetDataUseCaseImpl(
            coinDeskRepository = get()
        )
    }

    factory<LoadDataRetrospectUseCase> {
        LoadDataRetrospectUseCaseImpl(coinDeskRepository = get())
    }


    factory<CoinDeskRepository> {
        CoinDeskRepositoryImpl(apiService = get(), realm = get())
    }


    viewModel {
        DetailViewModel(case = get())
    }

    viewModel {
        ShareMainViewModel()
    }


}