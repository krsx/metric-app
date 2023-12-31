package com.capstone.metricapp.core.domain.usecase

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.User
import com.capstone.metricapp.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: IUserRepository) :
    UserUseCase {
    override fun loginUser(email: String, password: String): Flow<Resource<User>> {
        return userRepository.loginUser(email, password)
    }

    override fun registerUser(
        email: String,
        password: String,
        division: String
    ): Flow<Resource<User>> {
        return userRepository.registerUser(email, password, division)
    }

    override fun getUserToken(): Flow<String> {
        return userRepository.getUserToken()
    }

    override suspend fun saveUserToken(token: String) {
        return userRepository.saveUserToken(token)
    }

    override suspend fun deleteCache() {
        return userRepository.deleteCache()
    }

    override fun getUserDivision(): Flow<String> {
        return userRepository.getUserDivision()
    }

    override suspend fun saveUserDivision(division: String) {
        return userRepository.saveUserDivision(division)
    }

    override fun getUserEmail(): Flow<String> {
        return userRepository.getUserEmail()
    }

    override suspend fun saveUserEmail(email: String) {
        return userRepository.saveUserEmail(email)
    }
}