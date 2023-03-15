package com.example.mytestapp.di.share


import com.example.mytestapp.domain.db.AppDatabase
import com.example.mytestapp.utils.Const.ROOM_DATABASE_TABLE_COIN
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytestapp.data.source.remote.api.service.DataCoinDao
import io.realm.Realm
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {

    single<Realm> {
        Realm.getDefaultInstance()
    }
}