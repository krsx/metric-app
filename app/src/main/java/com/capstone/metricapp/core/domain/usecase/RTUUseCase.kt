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
//
//        telkom_merk: String,
//        telkom_type: String,
//        telkom_rangeVolt: String,
//        telkom_date: String,
//        telkom_sn: String,
//
//        main_sim_provider: String,
//        main_sim_number: String,
//
//        backup_sim_provider: String,
//        backup_sim_number: String,
//
//        rtu_merk: String,
//        rtu_type: String,
//        rtu_date: String,
//        rtu_sn: String,
//
//        bar_merk: String,
//        bat_type: String,
//        bat_date: String,
//
//        ): Flow<Resource<RTU>>
//
//    fun updateSpecLBSREC(
//        token: String,
//        id: String,
//        keypoint: String,
//
//        ): Flow<Resource<RTU>>
}