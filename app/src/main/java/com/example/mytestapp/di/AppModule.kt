package com.example.mytestapp.di

import com.example.mytestapp.di.feature.mainModule
import com.example.mytestapp.di.share.databaseModule
import com.example.mytestapp.di.share.networkModule
import com.example.mytestapp.di.share.preferenceModule
import com.example.mytestapp.di.share.roomModule

val appModule = listOf(
    networkModule,
    roomModule,
    mainModule,
    databaseModule,
)