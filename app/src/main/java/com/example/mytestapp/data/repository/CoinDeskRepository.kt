package com.example.mytestapp.data.repository


import android.util.Log
import com.example.mytestapp.data.model.room.CoinEntity
import com.example.mytestapp.data.source.remote.api.response.CoinDeskResponse
import com.example.mytestapp.data.source.remote.api.service.APIService
import com.example.mytestapp.data.source.remote.api.service.DataCoinDao
import com.google.gson.Gson
import io.realm.Realm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException


interface CoinDeskRepository {
    fun coinDesk(): Flow<CoinDeskResponse>
    fun saveDataCoinDeskLocal(coin: CoinEntity): Flow<Unit>
    fun getDataCoinDeskFromLocal(): Flow<List<CoinEntity>>
}

class CoinDeskRepositoryImpl(
    private val apiService: APIService,
    private val realm: Realm,
) : CoinDeskRepository {

    override fun coinDesk(): Flow<CoinDeskResponse> = flow {
        val response = apiService.getCoinDesk()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(body)
            } else {
                throw HttpException(response)
            }
        } else {
            throw HttpException(response)
        }
    }

    override fun saveDataCoinDeskLocal(coin: CoinEntity): Flow<Unit> = flow {
        Log.d("saveDataCoinDeskLocal", "$coin")
        runCatching {
            realm.beginTransaction()
            realm.insertOrUpdate(coin)
            realm.commitTransaction()
        }.onSuccess {
            emit(Unit)
        }.onFailure { exception ->
            throw exception
        }
    }

    override fun getDataCoinDeskFromLocal(): Flow<List<CoinEntity>> = flow {
        runCatching {
            realm.where(CoinEntity::class.java).findAll()
        }.onSuccess { privacyPolicyEntiry ->
            emit(privacyPolicyEntiry)
        }.onFailure { exception ->
            throw exception
        }
    }

}