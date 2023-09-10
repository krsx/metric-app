package com.capstone.metricapp.core.utils.datamapper

import com.capstone.metricapp.core.data.source.remote.response.CreateRTUData
import com.capstone.metricapp.core.data.source.remote.response.KeypointHistoryItem
import com.capstone.metricapp.core.data.source.remote.response.RTUData
import com.capstone.metricapp.core.data.source.remote.response.RTUItem
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.domain.model.RTU
import org.json.JSONObject


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
            notes = it.notes ?: ""
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
        notes = input.rtuItem.notes ?: ""
    )

    fun mapCreateRTUResponse(input: CreateRTUData?): RTU = RTU(
        id = input?.id!!,
        uniqueId = input.uniqueID!!,
        keypoint = input.keypoint!!,
        region = input.region!!,

        telkom_date = input.telkom_date ?: "",
        telkom_merk = input.telkom_merk ?: "",
        telkom_rangeVolt = input.telkom_rangeVolt ?: "",
        telkom_sn = input.telkom_sn ?: "",
        telkom_type = input.telkom_type ?: "",

        main_sim_provider = input.main_sim_provider ?: "",
        main_sim_number = input.main_sim_number ?: "",

        backup_sim_number = input.backup_sim_number ?: "",
        backup_sim_provider = input.backup_sim_provider ?: "",

        rtu_date = input.rtu_date ?: "",
        rtu_merk = input.rtu_merk ?: "",
        rtu_sn = input.rtu_sn ?: "",
        rtu_type = input.rtu_type ?: "",

        bat_merk = input.bat_merk ?: "",
        bat_date = input.bat_date ?: "",
        bat_type = input.bat_type ?: "",

        rect_date = input.rect_date ?: "",
        rect_type = input.rect_type ?: "",
        rect_merk = input.rect_merk ?: "",
        rect_rangeVolt = input.rect_rangeVolt ?: "",
        rect_sn = input.rect_sn ?: "",

        gat_date = input.gat_date ?: "",
        gat_type = input.gat_type ?: "",
        gat_merk = input.gat_merk ?: "",
        gat_sn = input.gat_sn ?: "",

        dateCreated = input.createdAt!!,
        notes = input.notes ?: ""
    )

    fun mapHistoryRTUResponseToDomain(input: List<KeypointHistoryItem?>?): List<KeypointHistory> =
        input!!.map {
            KeypointHistory(
                id = it?.id!!,
                documentId = it.documentId!!,
                modelName = it.modelName!!,
                fieldName = it.fieldName!!,
                oldValue = it.oldValue!!,
                newValue = it.newValue!!,
            )
        }

    fun getNewValueFieldFromJSON(input: String): RTU {
        input.let { newValue ->
            val newValueJSON = JSONObject(newValue)
            return RTU(
                id = newValueJSON.getString("_id"),
                uniqueId = newValueJSON.getString("uniqueID"),
                keypoint = newValueJSON.getString("keypoint"),
                region = newValueJSON.getString("lokasi"),

                telkom_merk = newValueJSON.getString("telkom_merk"),
                telkom_type = newValueJSON.getString("telkom_tipe"),
                telkom_rangeVolt = newValueJSON.getString("telkom_rangeVolt"),
                telkom_date = newValueJSON.getString("telkom_tanggalPenggantian"),
                telkom_sn = newValueJSON.getString("telkom_sn"),

                main_sim_provider = newValueJSON.getString("providerSimUtama"),
                main_sim_number = newValueJSON.getString("nomorSimUtama"),
                backup_sim_provider = newValueJSON.getString("providerSimCadangan"),
                backup_sim_number = newValueJSON.getString("nomorSimCadangan"),

                rtu_merk = newValueJSON.getString("rtu_merk"),
                rtu_type = newValueJSON.getString("rtu_tipe"),
                rtu_date = newValueJSON.getString("rtu_tanggalPenggatian"),
                rtu_sn = newValueJSON.getString("rtu_sn"),

                bat_merk = newValueJSON.getString("btr_merk"),
                bat_type = newValueJSON.getString("btr_tipe"),
                bat_date = newValueJSON.getString("btr_tanggalPenggatian"),

                rect_merk = newValueJSON.getString("rect_merk"),
                rect_type = newValueJSON.getString("rect_tipe"),
                rect_rangeVolt = newValueJSON.getString("rect_rangeVolt"),
                rect_date = newValueJSON.getString("rect_tanggalPenggantian"),
                rect_sn = newValueJSON.getString("rect_sn"),

                gat_merk = newValueJSON.getString("gtwy_merk"),
                gat_type = newValueJSON.getString("gtwy_tipe"),
                gat_date = newValueJSON.getString("gtwy_tanggalPenggatian"),
                gat_sn = newValueJSON.getString("gtwy_sn"),

                //dateCreated in history shows when the spec get updated
                //the "createdAt" field will only show the creation of the scadatel keypoint
                dateCreated = newValueJSON.getString("createdAt"),
                notes = newValueJSON.getString("notes"),
            )
        }
    }

    fun getHistoryDateOnly(input: String): String {
        input.let {
            val newValueJSON = JSONObject(it)
            return newValueJSON.getString("createdAt")
        }
    }
}