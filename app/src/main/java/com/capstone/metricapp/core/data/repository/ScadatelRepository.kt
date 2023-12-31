package com.capstone.metricapp.core.data.repository

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.data.source.remote.NetworkBoundResource
import com.capstone.metricapp.core.data.source.remote.datasource.RemoteDataSource
import com.capstone.metricapp.core.data.source.remote.network.ApiResponse
import com.capstone.metricapp.core.data.source.remote.response.*
import com.capstone.metricapp.core.domain.model.Common
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.domain.repository.IScadatelRepository
import com.capstone.metricapp.core.utils.datamapper.CommonDataMapper
import com.capstone.metricapp.core.utils.datamapper.ScadatelDataMapper
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScadatelRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IScadatelRepository {
    override fun getAllScadatel(token: String): Flow<Resource<List<Scadatel>>> {
        return object : NetworkBoundResource<List<Scadatel>, ScadatelListItemResponse>() {
            override suspend fun fetchFromApi(response: ScadatelListItemResponse): List<Scadatel> {
                return ScadatelDataMapper.mapListScadatelResponsesToDomain(response.data?.item)
            }

            override suspend fun createCall(): Flow<ApiResponse<ScadatelListItemResponse>> {
                return remoteDataSource.getAllScadatel(token)
            }
        }.asFlow()
    }

    override fun getScadatelById(token: String, id: String): Flow<Resource<Scadatel>> {
        return object : NetworkBoundResource<Scadatel, ScadatelItemResponse>() {
            override suspend fun fetchFromApi(response: ScadatelItemResponse): Scadatel {
                return ScadatelDataMapper.mapScadatelResponseToDomain(response.data)
            }

            override suspend fun createCall(): Flow<ApiResponse<ScadatelItemResponse>> {
                return remoteDataSource.getScadatelById(token, id)
            }
        }.asFlow()
    }

    override fun findScadatelKeypoints(
        token: String,
        keyword: String
    ): Flow<Resource<List<Scadatel>>> {
        return object : NetworkBoundResource<List<Scadatel>, ScadatelListItemResponse>() {
            override suspend fun fetchFromApi(response: ScadatelListItemResponse): List<Scadatel> {
                return ScadatelDataMapper.mapListScadatelResponsesToDomain(response.data?.item)
            }

            override suspend fun createCall(): Flow<ApiResponse<ScadatelListItemResponse>> {
                return remoteDataSource.findScadatelKeypoints(token, keyword)
            }

        }.asFlow()

    }

    override fun createScadatelKeypoint(
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
        notes: String,
        device: String,
        username: String
    ): Flow<Resource<Scadatel>> {
        return object : NetworkBoundResource<Scadatel, CreateScadatelItemResponse>() {
            override suspend fun fetchFromApi(response: CreateScadatelItemResponse): Scadatel {
                return ScadatelDataMapper.mapCreateScadatelResponseToDomain(response.data)
            }

            override suspend fun createCall(): Flow<ApiResponse<CreateScadatelItemResponse>> {
                return remoteDataSource.createScadatelKeypoint(
                    token,
                    uniqueId,
                    keypoint,
                    region,
                    merk,
                    type,
                    mainVolt,
                    backupVolt,
                    os,
                    date,
                    notes,
                    device, username,
                )
            }
        }.asFlow()
    }

    override fun updateSpecScadatel(
        token: String,
        id: String,
        merk: String?,
        type: String?,
        mainVolt: String?,
        backupVolt: String?,
        os: String?,
        date: String?,
        notes: String?,
        device: String?,
        username: String?,
    ): Flow<Resource<Scadatel>> {
        return object : NetworkBoundResource<Scadatel, UpdateScadatelItemResponse>() {
            override suspend fun fetchFromApi(response: UpdateScadatelItemResponse): Scadatel {
                return ScadatelDataMapper.mapScadatelResponseToDomain(response.data)
            }

            override suspend fun createCall(): Flow<ApiResponse<UpdateScadatelItemResponse>> {
                return remoteDataSource.updateSpecScadatel(
                    token,
                    id,
                    merk ?: "",
                    type ?: "",
                    mainVolt ?: "",
                    backupVolt ?: "",
                    os ?: "",
                    date ?: "",
                    notes ?: "",
                    device ?: "",
                    username ?: "",
                )
            }
        }.asFlow()
    }

    override fun getHistoryScadatel(
        token: String,
        uniqueId: String,
    ): Flow<Resource<List<KeypointHistory>>> {
        return object : NetworkBoundResource<List<KeypointHistory>, KeypointHistoryResponse>() {
            override suspend fun fetchFromApi(response: KeypointHistoryResponse): List<KeypointHistory> {
                return ScadatelDataMapper.mapHistoryScadatelResponseToDomain(response.data?.allHistory)
            }

            override suspend fun createCall(): Flow<ApiResponse<KeypointHistoryResponse>> {
                return remoteDataSource.getHistoryScadatel(token, uniqueId)
            }
        }.asFlow()
    }

    override fun deleteScadatelKeypoint(token: String, id: String): Flow<Resource<Common>> {
        return object : NetworkBoundResource<Common, CommonResponse>() {
            override suspend fun fetchFromApi(response: CommonResponse): Common {
                return CommonDataMapper.mapCommonResponseToDomain(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<CommonResponse>> {
                return remoteDataSource.deleteScadatelKeypoint(token, id)
            }
        }.asFlow()
    }

    override fun exportScadatelDataToPDF(token: String, id: String): Flow<Resource<ResponseBody>> {
        return object : NetworkBoundResource<ResponseBody, ResponseBody>() {
            override suspend fun fetchFromApi(response: ResponseBody): ResponseBody {
                return response
            }

            override suspend fun createCall(): Flow<ApiResponse<ResponseBody>> {
                return remoteDataSource.exportScadatelDataToPDF(token, id)
            }
        }.asFlow()
    }

    override fun exportScadatelDataToExcel(
        token: String,
        id: String
    ): Flow<Resource<ResponseBody>> {
        return object : NetworkBoundResource<ResponseBody, ResponseBody>() {
            override suspend fun fetchFromApi(response: ResponseBody): ResponseBody {
                return response
            }

            override suspend fun createCall(): Flow<ApiResponse<ResponseBody>> {
                return remoteDataSource.exportScadatelDataToExcel(token, id)
            }
        }.asFlow()
    }
}