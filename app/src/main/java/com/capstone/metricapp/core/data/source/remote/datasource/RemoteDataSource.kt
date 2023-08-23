package com.capstone.metricapp.core.data.source.remote.datasource

import android.util.Log
import com.capstone.metricapp.core.data.source.remote.network.ApiResponse
import com.capstone.metricapp.core.data.source.remote.network.ApiService
import com.capstone.metricapp.core.data.source.remote.response.*
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
    suspend fun getAllScadatel(token: String): Flow<ApiResponse<ScadatelListItemResponse>> {
        return flow {
            try {
                val response = apiService.getAllScadatel(token)
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

    suspend fun getScadatelById(
        token: String,
        id: String
    ): Flow<ApiResponse<ScadatelItemResponse>> {
        return flow {
            try {
                val response = apiService.getScadatelById(token, id)
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

    suspend fun findScadatelKeypoints(
        token: String,
        keyword: String
    ): Flow<ApiResponse<ScadatelListItemResponse>> {
        return flow {
            try {
                val response = apiService.findScadatelKeypoint(token, keyword)
                val data = response.data?.item
                if (data!!.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("findScadatelKeypoints", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createScadatelKeypoint(
        token: String,
        uniqueId: String,
        keypoint: String,
        region: String,
        merk: String,
        type: String,
        mainVolt: String,
        backupVolt: String,
        os: String,
        date: String,
    ): Flow<ApiResponse<ScadatelItemResponse>> {
        return flow {
            try {
                val response = apiService.createScadatelKeypoint(
                    token,
                    uniqueId,
                    keypoint,
                    region,
                    merk,
                    type,
                    mainVolt,
                    backupVolt,
                    os,
                    date
                )
                val data = response.data
                if (data != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("createScadatelKeypoint", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateSpecScadatel(
        token: String,
        id: String,
        keypoint: String,
        region: String?,
        merk: String?,
        type: String?,
        mainVolt: String?,
        backupVolt: String?,
        os: String?,
        date: String?
    ): Flow<ApiResponse<ScadatelItemResponse>> {
        return flow {
            try {
                val response = apiService.updateSpecScadatel(
                    token,
                    id,
                    id,
                    keypoint,
                    region ?: "",
                    merk ?: "",
                    type ?: "",
                    mainVolt ?: "",
                    backupVolt ?: "",
                    os ?: "",
                    date ?: "",
                )
                val data = response.data
                if (data != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("createScadatelKeypoint", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteScadatelKeypoint(
        token: String,
        id: String
    ): Flow<ApiResponse<CommonResponse>> {
        return flow {
            try {
                val response = apiService.deleteScadatelKeypoint(token, id)
                val data = response.status
                if (data!!.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("deleteScadatelKeypoint", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}