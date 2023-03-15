package com.example.mytestapp

import android.app.Application
import com.example.mytestapp.di.appModule
import com.example.mytestapp.domain.db.AppDatabase
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}