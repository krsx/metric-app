package com.capstone.metricapp.core.utils.datamapper

import com.capstone.metricapp.core.data.source.remote.response.RTUData
import com.capstone.metricapp.core.data.source.remote.response.RTUItem
import com.capstone.metricapp.core.domain.model.RTU

object RTUDataMapper {
    fun mapListRTUResponseToDomain(input: List<RTUItem?>?): List<RTU> = input!!.map {
        RTU(
            id = it?.id!!,
            uniqueId = it.uniqueID!!,
            keypoint = it.keypoint!!,
            region = it.region!!,

            telkom_date = it.telkomDate ?: "",
            telkom_merk = it.telkomMerk ?: "",
            telkom_rangeVolt = it.telkomRangeVolt ?: "",
            telkom_sn = it.telkomSn ?: "",
            telkom_type = it.telkomType ?: "",

            main_sim_provider = it.mainSimProvider ?: "",
            main_sim_number = it.mainSimNumber ?: "",

            backup_sim_number = it.backupSimNumber ?: "",
            backup_sim_provider = it.backupSimProvider ?: "",

            rtu_date = it.rtuDate ?: "",
            rtu_merk = it.rtuMerk ?: "",
            rtu_sn = it.rtuSn ?: "",
            rtu_type = it.rtuType ?: "",

            bat_merk = it.batMerk ?: "",
            bat_date = it.batDate ?: "",
            bat_type = it.batType ?: "",

            rect_date = it.rectDate ?: "",
            rect_type = it.rectType ?: "",
            rect_merk = it.rectMerk ?: "",
            rect_rangeVolt = it.rectRangeVolt ?: "",
            rect_sn = it.rectSn ?: "",

            gat_date = it.gatDate ?: "",
            gat_type = it.gatType ?: "",
            gat_merk = it.gatMerk ?: "",
            gat_sn = it.gatSn ?: "",

            dateCreated = it.createdAt!!,
        )
    }

    fun mapRTUResponseToDomain(input: RTUData?): RTU = RTU(
        id = input?.rtuItem?.id!!,
        uniqueId = input.rtuItem.uniqueID!!,
        keypoint = input.rtuItem.keypoint!!,
        region = input.rtuItem.region!!,

        telkom_date = input.rtuItem.telkomDate ?: "",
        telkom_merk = input.rtuItem.telkomMerk ?: "",
        telkom_rangeVolt = input.rtuItem.telkomRangeVolt ?: "",
        telkom_sn = input.rtuItem.telkomSn ?: "",
        telkom_type = input.rtuItem.telkomType ?: "",

        main_sim_provider = input.rtuItem.mainSimProvider ?: "",
        main_sim_number = input.rtuItem.mainSimNumber ?: "",

        backup_sim_number = input.rtuItem.backupSimNumber ?: "",
        backup_sim_provider = input.rtuItem.backupSimProvider ?: "",

        rtu_date = input.rtuItem.rtuDate ?: "",
        rtu_merk = input.rtuItem.rtuMerk ?: "",
        rtu_sn = input.rtuItem.rtuSn ?: "",
        rtu_type = input.rtuItem.rtuType ?: "",

        bat_merk = input.rtuItem.batMerk ?: "",
        bat_date = input.rtuItem.batDate ?: "",
        bat_type = input.rtuItem.batType ?: "",

        rect_date = input.rtuItem.rectDate ?: "",
        rect_type = input.rtuItem.rectType ?: "",
        rect_merk = input.rtuItem.rectMerk ?: "",
        rect_rangeVolt = input.rtuItem.rectRangeVolt ?: "",
        rect_sn = input.rtuItem.rectSn ?: "",

        gat_date = input.rtuItem.gatDate ?: "",
        gat_type = input.rtuItem.gatType ?: "",
        gat_merk = input.rtuItem.gatMerk ?: "",
        gat_sn = input.rtuItem.gatSn ?: "",

        dateCreated = input.rtuItem.createdAt!!,
    )
}