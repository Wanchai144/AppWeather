package com.example.mytestapp.domain.usecase

import com.example.mytestapp.data.model.UserEntity
import com.example.mytestapp.data.repository.AttendWorkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AttendWorkUseCase {
    fun executeGetData(): Flow<List<UserEntity>>
    fun executeSaveData(employeeID:String,message: String): Flow<Unit?>
}

class AttendWorkUseCaseImpl(
    private val attendWorkRepository: AttendWorkRepository
) : AttendWorkUseCase {
    private var currentId = 0

    override fun executeGetData(): Flow<List<UserEntity>> = flow {
        attendWorkRepository.getUser().collect {
            emit(it)
        }
    }

    override fun executeSaveData(employeeID:String,message: String): Flow<Unit> = flow {
        currentId++
        val userList : ArrayList<UserEntity> = arrayListOf()
        userList.add(UserEntity(id = currentId,employeeID = employeeID,message = message))
        attendWorkRepository.saveData(userList).collect {
            emit(it)
        }
    }
}