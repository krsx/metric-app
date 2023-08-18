package com.capstone.metricapp.core.data.repository

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.data.source.local.LocalDataSource
import com.capstone.metricapp.core.data.source.remote.NetworkBoundResource
import com.capstone.metricapp.core.data.source.remote.datasource.RemoteDataSource
import com.capstone.metricapp.core.data.source.remote.network.ApiResponse
import com.capstone.metricapp.core.data.source.remote.response.LoginResponse
import com.capstone.metricapp.core.data.source.remote.response.RegisterResponse
import com.capstone.metricapp.core.domain.model.User
import com.capstone.metricapp.core.domain.repository.IUserRepository
import com.capstone.metricapp.core.utils.datamapper.UserDataMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IUserRepository {
    override fun loginUser(email: String, password: String): Flow<Resource<User>> {
        return object : NetworkBoundResource<User, LoginResponse>() {
            override suspend fun fetchFromApi(response: LoginResponse): User {
                return UserDataMapper.mapLoginResponseToDomain(response.data?.user)
            }

            override suspend fun createCall(): Flow<ApiResponse<LoginResponse>> {
                return remoteDataSource.loginUser(email, password)
            }
        }.asFlow()
    }

    override fun registerUser(
        email: String,
        password: String,
        division: String
    ): Flow<Resource<User>> {
        return object : NetworkBoundResource<User, RegisterResponse>() {
            override suspend fun fetchFromApi(response: RegisterResponse): User {
                return UserDataMapper.mapRegisterResponseToDomain(response.data?.user)
            }

            override suspend fun createCall(): Flow<ApiResponse<RegisterResponse>> {
                return remoteDataSource.registerUser(email, password, division)
            }
        }.asFlow()
    }

    override fun getUserToken(): Flow<String> {
        return localDataSource.getUserToken()
    }

    override suspend fun saveUserToken(token: String) {
        return localDataSource.saveUserToken(token)
    }

    override suspend fun deleteCache() {
        return localDataSource.deleteCache()
    }

    override fun getUserDivision(): Flow<String> {
        return localDataSource.getUserDivision()
    }

    override suspend fun saveUserDivision(division: String) {
        return localDataSource.saveUserDivision(division)
    }


}