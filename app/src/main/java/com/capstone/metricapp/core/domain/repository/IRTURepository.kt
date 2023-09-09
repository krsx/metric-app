package com.capstone.metricapp.core.domain.repository

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Common
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.domain.model.RTU
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface IRTURepository {

    fun getAllRTU(token: String): Flow<Resource<List<RTU>>>

    fun getRTUById(token: String, id: String): Flow<Resource<RTU>>

    fun findRTUKeypoints(token: String, keyword: String): Flow<Resource<List<RTU>>>

    fun createLBSKeypoint(
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

        notes: String
    ): Flow<Resource<RTU>>

    fun updateSpecLBSRECKeypoint(
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

        bat_merk: String?,
        bat_type: String?,
        bat_date: String?,

        notes: String?
    ): Flow<Resource<RTU>>

    fun createGIGHKeypoint(
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

        bat_merk: String,
        bat_type: String,
        bat_date: String,

        gat_merk: String,
        gat_type: String,
        gat_date: String,
        gat_sn: String,

        notes: String
    ): Flow<Resource<RTU>>

    fun updateSpecGIGHKeypoint(
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

        bat_merk: String?,
        bat_type: String?,
        bat_date: String?,

        gat_merk: String?,
        gat_type: String?,
        gat_date: String?,
        gat_sn: String?,

        notes: String?
    ): Flow<Resource<RTU>>

    fun getHistoryRTU(
        token: String,
        uniqueId: String,
    ): Flow<Resource<List<KeypointHistory>>>

    fun deleteRTUKeypoint(token: String, id: String): Flow<Resource<Common>>

    fun exportRTUDataToPDF(token: String, id: String): Flow<Resource<ResponseBody>>

    fun exportRTUDataToExcel(token: String, id: String): Flow<Resource<ResponseBody>>

}