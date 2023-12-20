package com.example.mytestapp.di.share

import io.reactivex.schedulers.Schedulers.single
import io.realm.Realm
import org.koin.dsl.module

val databaseModule = module {

    single<Realm> {
        Realm.getDefaultInstance()
    }
}