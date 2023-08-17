package com.capstone.metricapp.core.domain.repository

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun loginUser(email: String, password: String): Flow<Resource<User>>

    fun registerUser(email: String, password: String, division: String): Flow<Resource<User>>

    fun getUserToken(): Flow<String>

    suspend fun saveUserToken(token: String)

    suspend fun deleteCache()
}