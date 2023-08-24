package com.capstone.metricapp.core.domain.repository

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Common
import com.capstone.metricapp.core.domain.model.RTU
import kotlinx.coroutines.flow.Flow

interface IRTURepository {

    fun getAllRTU(token: String): Flow<Resource<List<RTU>>>

    fun getRTUById(token: String, id: String): Flow<Resource<RTU>>

    fun findRTUKeypoints(token: String, keyword: String): Flow<Resource<List<RTU>>>

    fun deleteRTUKeypoint(token: String, id: String): Flow<Resource<Common>>
}