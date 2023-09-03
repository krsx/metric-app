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
    ): Flow<ApiResponse<CreateScadatelItemResponse>> {
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
        merk: String?,
        type: String?,
        mainVolt: String?,
        backupVolt: String?,
        os: String?,
        date: String?
    ): Flow<ApiResponse<UpdateScadatelItemResponse>> {
        return flow {
            try {
                val response = apiService.updateSpecScadatel(
                    token,
                    id,
                    id,
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

    suspend fun getHistoryScadatel(
        token: String,
        id: String
    ): Flow<ApiResponse<ScadatelHistoryResponse>> {
        return flow {
            try {
                val response = apiService.getScadatelHistory(token, id)
                val data = response.data
                if (data != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getHistoryScadatel", e.toString())
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

    //RTU
    suspend fun getAllRTU(token: String): Flow<ApiResponse<RTUListItemResponse>> {
        return flow {
            try {
                val response = apiService.getAllRTU(token)
                val data = response.data?.item
                if (data!!.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getAllRTU", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getRTUById(token: String, id: String): Flow<ApiResponse<RTUItemResponse>> {
        return flow {
            try {
                val response = apiService.getRTUById(token, id)
                val data = response.data
                if (data != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getRTUById", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun findRTUKeypoints(
        token: String,
        keyword: String
    ): Flow<ApiResponse<RTUListItemResponse>> {
        return flow {
            try {
                val response = apiService.findRTUKeypoint(token, keyword)
                val data = response.data?.item

                if (data!!.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("findRTUKeypoints", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createLBSKeypoint(
        token: String,
        uniqueId: String,
        keypoint: String,
        region: String,

        telkom_merk: String,
        telkom_type: String,
        telkom_rangeVolt: String,
        telkom_date: String,
        telkom_sn: String,

        main_sim_provider: String,
        main_sim_number: String,

        backup_sim_provider: String,
        backup_sim_number: String,

        rtu_merk: String,
        rtu_type: String,
        rtu_date: String,
        rtu_sn: String,

        bat_merk: String,
        bat_type: String,
        bat_date: String,
    ): Flow<ApiResponse<CreateRTUItemResponse>> {
        return flow {
            try {
                val response = apiService.createLBSRECKeypoint(
                    token,
                    uniqueId,
                    keypoint,
                    region,
                    telkom_merk,
                    telkom_type,
                    telkom_rangeVolt,
                    telkom_date,
                    telkom_sn,
                    main_sim_provider,
                    main_sim_number,
                    backup_sim_provider,
                    backup_sim_number,
                    rtu_merk,
                    rtu_type,
                    rtu_date,
                    rtu_sn,
                    bat_merk,
                    bat_type,
                    bat_date
                )
                val data = response.data
                if (data != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("createLBSKeypoint", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createGIGHKeypoint(
        token: String,
        uniqueId: String,
        keypoint: String,
        region: String,

        telkom_merk: String,
        telkom_type: String,
        telkom_rangeVolt: String,
        telkom_date: String,
        telkom_sn: String,

        rect_merk: String,
        rect_type: String,
        rect_rangeVolt: String,
        rect_date: String,
        rect_sn: String,

        rtu_merk: String,
        rtu_type: String,
        rtu_date: String,
        rtu_sn: String,

        bar_merk: String,
        bat_type: String,
        bat_date: String,

        gat_merk: String,
        gat_type: String,
        gat_date: String,
        gat_sn: String,
    ): Flow<ApiResponse<CreateRTUItemResponse>> {
        return flow<ApiResponse<CreateRTUItemResponse>> {
            try {
                val response = apiService.createGIGHKeypoint(
                    token, uniqueId, keypoint, region, telkom_merk,
                    telkom_type,
                    telkom_rangeVolt,
                    telkom_date,
                    telkom_sn,
                    rect_merk,
                    rect_type,
                    rect_rangeVolt,
                    rect_date,
                    rect_sn,
                    rtu_merk,
                    rtu_type,
                    rtu_date,
                    rtu_sn,
                    bar_merk,
                    bat_type,
                    bat_date,
                    gat_merk,
                    gat_type, gat_date, gat_sn,
                )
                val data = response.data
                if (data != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("createGIGHKeypoint", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getHistoryRTU(
        token: String,
        id: String
    ): Flow<ApiResponse<ScadatelHistoryResponse>> {
        return flow {
            try {
                val response = apiService.getScadatelHistory(token, id)
                val data = response.data
                if (data != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getHistoryRTU", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteRTUKeypoint(token: String, id: String): Flow<ApiResponse<CommonResponse>> {
        return flow {
            try {
                val response = apiService.deleteRTUKeypoint(token, id)
                val data = response.status
                if (data!!.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("deleteRTUKeypoint", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}