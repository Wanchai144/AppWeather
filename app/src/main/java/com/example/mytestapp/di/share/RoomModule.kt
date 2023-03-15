package com.example.mytestapp.di.share

import androidx.room.Room
import com.example.mytestapp.domain.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val roomModule = module {
//    single {
//        Room.databaseBuilder(androidContext(), AppDatabase::class.java, ROOM_DATABASE_TABLE_COIN)
//            .fallbackToDestructiveMigration()
//            .addCallback(object : RoomDatabase.Callback() {
//            })
//            .build()
//    }
//
//    single { get<AppDatabase>().dataCoinDao() }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database").build()
       // db.dataCoinDao()
    }
    //single { DataCoinDao }
    single { get<AppDatabase>().dataCoinDao() }

//    factory<AppDataRoomRepository> {
//        AppDataRoomRepositoryImpl(get())
//    }
}