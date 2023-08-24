package com.capstone.metricapp.core.domain.usecase

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Common
import com.capstone.metricapp.core.domain.model.RTU
import kotlinx.coroutines.flow.Flow

interface RTUUseCase {

    fun getAllRTU(token: String): Flow<Resource<List<RTU>>>

    fun getRTUById(token: String, id: String): Flow<Resource<RTU>>

    fun findRTUKeypoints(token: String, keyword: String): Flow<Resource<List<RTU>>>

    fun deleteRTUKeypoint(token: String, id: String): Flow<Resource<Common>>

//    fun createLBSRECKeypoint(
//        token: String,
//        uniqueId: String,
//        keypoint: String,
//        region: String,
//    ):
}