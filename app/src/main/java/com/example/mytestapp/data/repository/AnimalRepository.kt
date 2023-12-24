package com.example.mytestapp.data.repository

import com.example.mytestapp.data.model.UserEntity
import io.realm.Realm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AnimalRepository {
    fun getUser(): Flow<List<UserEntity>>
    fun saveData(userEntity: List<UserEntity>): Flow<Unit>

}

class AttendWorkRepositoryImpl(
    private val realm: Realm,
) : AnimalRepository {

    override fun getUser(): Flow<List<UserEntity>> = flow {
        runCatching {
            realm.where(UserEntity::class.java).findAll()
        }.onSuccess { userLoginEntity ->
            emit(userLoginEntity)
        }.onFailure { exception ->
            throw exception
        }
    }

    override fun saveData(userEntity: List<UserEntity>): Flow<Unit> = flow {
        runCatching {
            realm.beginTransaction()
            realm.insertOrUpdate(userEntity)
            realm.commitTransaction()
        }.onSuccess {
            emit(Unit)
        }.onFailure { exception ->
            throw exception
        }
    }

}
