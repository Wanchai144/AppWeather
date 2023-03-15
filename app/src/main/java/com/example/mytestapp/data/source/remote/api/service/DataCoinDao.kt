package com.example.mytestapp.data.source.remote.api.service


import androidx.room.*
import com.example.mytestapp.data.model.room.CoinEntity

@Dao
interface DataCoinDao {

    /* Insert or replace */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(entityList: CoinEntity)
//
//    @Insert
//    fun insertOrReplace(user: User):Maybe<Long>
}