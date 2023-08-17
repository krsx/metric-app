package com.capstone.metricapp.core.data.source.remote.datasource

import android.util.Log
import com.capstone.metricapp.core.data.source.remote.network.ApiResponse
import com.capstone.metricapp.core.data.source.remote.network.ApiService
import com.capstone.metricapp.core.data.source.remote.response.LoginResponse
import com.capstone.metricapp.core.data.source.remote.response.RegisterResponse
import com.capstone.metricapp.core.data.source.remote.response.ScadatelItemResponse
import com.capstone.metricapp.core.data.source.remote.response.ScadatelListItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    //USER
    suspend fun loginUser(email: String, password: String): Flow<ApiResponse<LoginResponse>> {
        return flow {
            try {
                val response = apiService.loginUser(email, password)
                val data = response.data?.user
                if (data != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Log.e("loginUser", e.toString())
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun registerUser(
        email: String,
        password: String,
        divisions: String
    ): Flow<ApiResponse<RegisterResponse>> {
        return flow {
            try {
                val response = apiService.registerUser(email, password, divisions)
                val data = response.data?.user
                if (data != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Log.e("registerUser", e.toString())
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    //SCADATEL
    suspend fun getAllScadatel(): Flow<ApiResponse<ScadatelListItemResponse>> {
        return flow {
            try {
                val response = apiService.getAllScadatel()
                val dataArray = response.data?.item
                if (dataArray!!.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Log.e("getAllScadatel", e.toString())
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getScadatelById(id: String): Flow<ApiResponse<ScadatelItemResponse>> {
        return flow {
            try {
                val response = apiService.getScadatelById(id)
                val data = response.data
                if (data != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getAllScadatelById", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}