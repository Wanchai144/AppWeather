package com.example.mytestapp.domain.usecase

import com.example.mytestapp.data.model.UserEntity
import com.example.mytestapp.data.repository.AnimalRepository
import io.realm.RealmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AnimalUseCase {
    fun executeGetData(): Flow<List<UserEntity>>
    fun executeSaveData(): Flow<Unit?>
}

class AnimalUseCaseImpl(
    private val animalRepository: AnimalRepository
) : AnimalUseCase {
    private var currentId = 0
    override fun executeGetData(): Flow<List<UserEntity>> = flow {
        animalRepository.getUser().collect {
            emit(it)
        }
    }

    override fun executeSaveData(): Flow<Unit> = flow {
        currentId++
        val data = listOf("Ant","Dog","Cat","Bee","Pig","Wolf","Fish","Fox","Worm","Zebra","Lion")
        val userList : ArrayList<UserEntity> = arrayListOf()
        val userEntity = UserEntity(id = currentId, name = RealmList(*data.toTypedArray()))
        userList.add(userEntity)
        animalRepository.saveData(userList).collect {
            emit(it)
        }
    }
}