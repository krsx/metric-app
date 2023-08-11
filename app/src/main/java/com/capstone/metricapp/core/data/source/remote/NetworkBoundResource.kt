package com.capstone.metricapp.core.data.source.remote

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(fetchFromApi(apiResponse.data)))
            }
            is ApiResponse.Message -> {
                emit(Resource.Message(apiResponse.message))
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error(apiResponse.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Message("Empty"))
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun fetchFromApi(response: RequestType): ResultType

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}