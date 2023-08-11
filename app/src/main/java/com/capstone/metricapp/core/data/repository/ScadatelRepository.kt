package com.capstone.metricapp.core.data.repository

import android.util.Log
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.data.source.remote.NetworkBoundResource
import com.capstone.metricapp.core.data.source.remote.datasource.RemoteDataSource
import com.capstone.metricapp.core.data.source.remote.network.ApiResponse
import com.capstone.metricapp.core.data.source.remote.response.ScadatelItemResponse
import com.capstone.metricapp.core.data.source.remote.response.ScadatelListItemResponse
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.domain.repository.IScadatelRepository
import com.capstone.metricapp.core.utils.ScadatelDataMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScadatelRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IScadatelRepository {
    override fun getAllScadatel(): Flow<Resource<List<Scadatel>>> {
        return object : NetworkBoundResource<List<Scadatel>, ScadatelListItemResponse>() {
            override suspend fun fetchFromApi(response: ScadatelListItemResponse): List<Scadatel> {
                Log.e("TEST",
                    ScadatelDataMapper.mapListScadatelResponsesToDomain(response.data?.item)
                        .toString()
                )
                return ScadatelDataMapper.mapListScadatelResponsesToDomain(response.data?.item)
            }

            override suspend fun createCall(): Flow<ApiResponse<ScadatelListItemResponse>> {
                return remoteDataSource.getAllScadatel()
            }
        }.asFlow()
    }

    override fun getScadatelById(id: String): Flow<Resource<Scadatel>> {
        return object : NetworkBoundResource<Scadatel, ScadatelItemResponse>() {
            override suspend fun fetchFromApi(response: ScadatelItemResponse): Scadatel {
                return ScadatelDataMapper.mapScadatelResponseToDomain(response.data)
            }

            override suspend fun createCall(): Flow<ApiResponse<ScadatelItemResponse>> {
                return remoteDataSource.getScadatelById(id)
            }
        }.asFlow()
    }
}