package com.example.mytestapp.di

import com.example.mytestapp.di.feature.mainModule
import com.example.mytestapp.di.share.appUtilsModule
import com.example.mytestapp.di.share.databaseModule
import com.example.mytestapp.di.share.networkModule
import com.example.mytestapp.di.share.preferencesModule

val appModule = listOf(
//    networkModule,
    preferencesModule,
    databaseModule,
    mainModule,
    appUtilsModule,
)