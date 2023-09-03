package com.capstone.metricapp.core.data.repository

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.data.source.remote.NetworkBoundResource
import com.capstone.metricapp.core.data.source.remote.datasource.RemoteDataSource
import com.capstone.metricapp.core.data.source.remote.network.ApiResponse
import com.capstone.metricapp.core.data.source.remote.response.CommonResponse
import com.capstone.metricapp.core.data.source.remote.response.RTUItemResponse
import com.capstone.metricapp.core.data.source.remote.response.RTUListItemResponse
import com.capstone.metricapp.core.domain.model.Common
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.domain.model.RTU
import com.capstone.metricapp.core.domain.repository.IRTURepository
import com.capstone.metricapp.core.utils.datamapper.CommonDataMapper
import com.capstone.metricapp.core.utils.datamapper.RTUDataMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RTURepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IRTURepository {
    override fun getAllRTU(token: String): Flow<Resource<List<RTU>>> {
        return object : NetworkBoundResource<List<RTU>, RTUListItemResponse>() {
            override suspend fun fetchFromApi(response: RTUListItemResponse): List<RTU> {
                return RTUDataMapper.mapListRTUResponseToDomain(response.data?.item)
            }

            override suspend fun createCall(): Flow<ApiResponse<RTUListItemResponse>> {
                return remoteDataSource.getAllRTU(token)
            }
        }.asFlow()
    }

    override fun getRTUById(token: String, id: String): Flow<Resource<RTU>> {
        return object : NetworkBoundResource<RTU, RTUItemResponse>() {
            override suspend fun fetchFromApi(response: RTUItemResponse): RTU {
                return RTUDataMapper.mapRTUResponseToDomain(response.data)
            }

            override suspend fun createCall(): Flow<ApiResponse<RTUItemResponse>> {
                return remoteDataSource.getRTUById(token, id)
            }
        }.asFlow()
    }

    override fun findRTUKeypoints(token: String, keyword: String): Flow<Resource<List<RTU>>> {
        return object : NetworkBoundResource<List<RTU>, RTUListItemResponse>() {
            override suspend fun fetchFromApi(response: RTUListItemResponse): List<RTU> {
                return RTUDataMapper.mapListRTUResponseToDomain(response.data?.item)
            }

            override suspend fun createCall(): Flow<ApiResponse<RTUListItemResponse>> {
                return remoteDataSource.findRTUKeypoints(token, keyword)
            }
        }.asFlow()
    }

    override fun createLBSKeypoint(
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
        bar_merk: String,
        bat_type: String,
        bat_date: String
    ): Flow<Resource<RTU>> {
        TODO("Not yet implemented")
    }

    override fun updateSpecLBS(
        token: String,
        uniqueId: String,
        telkom_merk: String?,
        telkom_type: String?,
        telkom_rangeVolt: String?,
        telkom_date: String?,
        telkom_sn: String?,
        main_sim_provider: String?,
        main_sim_number: String?,
        backup_sim_provider: String?,
        backup_sim_number: String?,
        rtu_merk: String?,
        rtu_type: String?,
        rtu_date: String?,
        rtu_sn: String?,
        bar_merk: String?,
        bat_type: String?,
        bat_date: String?
    ): Flow<Resource<RTU>> {
        TODO("Not yet implemented")
    }

    override fun createGIGHKeypoint(
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
        gat_sn: String
    ): Flow<Resource<RTU>> {
        TODO("Not yet implemented")
    }

    override fun updateSpecGIGHKeypoint(
        token: String,
        uniqueId: String,
        telkom_merk: String?,
        telkom_type: String?,
        telkom_rangeVolt: String?,
        telkom_date: String?,
        telkom_sn: String?,
        rect_merk: String?,
        rect_type: String?,
        rect_rangeVolt: String?,
        rect_date: String?,
        rect_sn: String?,
        rtu_merk: String?,
        rtu_type: String?,
        rtu_date: String?,
        rtu_sn: String?,
        bar_merk: String?,
        bat_type: String?,
        bat_date: String?,
        gat_merk: String?,
        gat_type: String?,
        gat_date: String?,
        gat_sn: String?
    ): Flow<Resource<RTU>> {
        TODO("Not yet implemented")
    }

    override fun getHistoryRTU(
        token: String,
        uniqueId: String
    ): Flow<Resource<List<KeypointHistory>>> {
        TODO("Not yet implemented")
    }

    override fun deleteRTUKeypoint(token: String, id: String): Flow<Resource<Common>> {
        return object : NetworkBoundResource<Common, CommonResponse>() {
            override suspend fun fetchFromApi(response: CommonResponse): Common {
                return CommonDataMapper.mapCommonResponseToDomain(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<CommonResponse>> {
                return remoteDataSource.deleteRTUKeypoint(token, id)
            }
        }.asFlow()
    }
}