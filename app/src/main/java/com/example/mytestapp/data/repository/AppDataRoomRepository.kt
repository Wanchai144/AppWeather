//package com.example.mytestapp.data.repository
//
//import android.util.Log
//import com.example.mytestapp.data.model.room.CoinEntity
//import com.example.mytestapp.data.source.remote.api.service.DataCoinDao
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//
//interface AppDataRoomRepository {
//    fun saveDataCoinDeskLocal(coin: CoinEntity): Flow<Unit>
//}
//
//class AppDataRoomRepositoryImpl(
//    private val dao: DataCoinDao,
//) : AppDataRoomRepository {
//
//
//    override fun saveDataCoinDeskLocal(coin: CoinEntity): Flow<Unit> = flow {
//        Log.d("saveDataCoinDeskLocal", "$coin")
//        runCatching {
//            dao.insertOrReplace(coin)
//        }.onSuccess {
//            emit(Unit)
//        }.onFailure { exception ->
//            throw exception
//        }
//    }
//
//}