package com.capstone.metricapp.core.domain.usecase

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Common
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.domain.model.RTU
import com.capstone.metricapp.core.domain.repository.IRTURepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RTUInteractor @Inject constructor(private val rtuRepository: IRTURepository) :
    RTUUseCase {
    override fun getAllRTU(token: String): Flow<Resource<List<RTU>>> {
        return rtuRepository.getAllRTU(token)
    }

    override fun getRTUById(token: String, id: String): Flow<Resource<RTU>> {
        return rtuRepository.getRTUById(token, id)
    }

    override fun findRTUKeypoints(token: String, keyword: String): Flow<Resource<List<RTU>>> {
        return rtuRepository.findRTUKeypoints(token, keyword)
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
        bat_merk: String,
        bat_type: String,
        bat_date: String
    ): Flow<Resource<RTU>> {
        return rtuRepository.createLBSKeypoint(
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
            bat_date,
        )
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
        bat_merk: String?,
        bat_type: String?,
        bat_date: String?
    ): Flow<Resource<RTU>> {
        return rtuRepository.updateSpecLBSRECKeypoint(
            token,
            uniqueId,
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
        bat_merk: String,
        bat_type: String,
        bat_date: String,
        gat_merk: String,
        gat_type: String,
        gat_date: String,
        gat_sn: String
    ): Flow<Resource<RTU>> {
        return rtuRepository.createGIGHKeypoint(
            token,
            uniqueId,
            keypoint,
            region,
            telkom_merk,
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
            bat_merk,
            bat_type,
            bat_date,
            gat_merk,
            gat_type,
            gat_date,
            gat_sn
        )
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
        bat_merk: String?,
        bat_type: String?,
        bat_date: String?,
        gat_merk: String?,
        gat_type: String?,
        gat_date: String?,
        gat_sn: String?
    ): Flow<Resource<RTU>> {
        return rtuRepository.updateSpecGIGHKeypoint(
            token,
            uniqueId,
            telkom_merk,
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
            bat_merk,
            bat_type,
            bat_date,
            gat_merk,
            gat_type,
            gat_date,
            gat_sn,
        )
    }

    override fun getHistoryRTU(
        token: String,
        uniqueId: String
    ): Flow<Resource<List<KeypointHistory>>> {
        return rtuRepository.getHistoryRTU(token, uniqueId)
    }

    override fun deleteRTUKeypoint(token: String, id: String): Flow<Resource<Common>> {
        return deleteRTUKeypoint(token, id)
    }
}