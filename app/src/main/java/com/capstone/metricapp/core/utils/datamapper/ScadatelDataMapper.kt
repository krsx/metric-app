package com.capstone.metricapp.core.utils.datamapper

import com.capstone.metricapp.core.data.source.remote.response.CreateScadatelData
import com.capstone.metricapp.core.data.source.remote.response.KeypointHistoryItem
import com.capstone.metricapp.core.data.source.remote.response.ScadatelData
import com.capstone.metricapp.core.data.source.remote.response.ScadatelItem
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.domain.model.Scadatel
import org.json.JSONObject

object ScadatelDataMapper {
    fun mapListScadatelResponsesToDomain(input: List<ScadatelItem?>?): List<Scadatel> =
        input!!.map {
            Scadatel(
                id = it?.id!!,
                uniqueId = it.uniqueID!!,
                keypoint = it.keypoint!!,
                region = it.region!!,
                merk = it.merk!!,
                type = it.type!!,
                mainVolt = it.mainVolt!!,
                backupVolt = it.backupVolt!!,
                os = it.os!!,
                date = it.date!!,
                dateCreated = it.createdAt!!,
                notes = it.notes ?: ""
            )
        }

    fun mapScadatelResponseToDomain(input: ScadatelData?): Scadatel = Scadatel(
        id = input?.scadatelItem?.id ?: "",
        uniqueId = input?.scadatelItem?.uniqueID ?: "",
        keypoint = input?.scadatelItem?.keypoint ?: "",
        region = input?.scadatelItem?.region ?: "",
        merk = input?.scadatelItem?.merk ?: "",
        type = input?.scadatelItem?.type ?: "",
        mainVolt = input?.scadatelItem?.mainVolt ?: "",
        backupVolt = input?.scadatelItem?.backupVolt ?: "",
        os = input?.scadatelItem?.os ?: "",
        date = input?.scadatelItem?.date ?: "",
        dateCreated = input?.scadatelItem?.createdAt ?: "",
        notes = input?.scadatelItem?.notes ?: "",
    )

    fun mapCreateScadatelResponseToDomain(input: CreateScadatelData?): Scadatel = Scadatel(
        id = input?.item?.id!!,
        uniqueId = input.item.uniqueID!!,
        keypoint = input.item.keypoint!!,
        region = input.item.region!!,
        merk = input.item.merk!!,
        type = input.item.type!!,
        mainVolt = input.item.mainVolt!!,
        backupVolt = input.item.backupVolt!!,
        os = input.item.os!!,
        date = input.item.date!!,
        dateCreated = input.item.createdAt!!,
        notes = input.item.notes!!,
    )

    fun mapHistoryScadatelResponseToDomain(input: List<KeypointHistoryItem?>?): List<KeypointHistory> =
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

    fun getNewValueFieldFromJSON(input: String): Scadatel {
        input.let { newValue ->
            val newValueJSON = JSONObject(newValue)
            return Scadatel(
                id = newValueJSON.getString("_id"),
                uniqueId = newValueJSON.getString("uniqueID"),
                keypoint = newValueJSON.getString("keypoint"),
                region = newValueJSON.getString("lokasi"),
                merk = newValueJSON.getString("merk"),
                type = newValueJSON.getString("tipe"),
                mainVolt = newValueJSON.getString("mainVolt"),
                backupVolt = newValueJSON.getString("backupVolt"),
                os = newValueJSON.getString("os"),
                date = newValueJSON.getString("tanggalPemasangan"),

                //dateCreated in history shows when the spec get updated
                //the "createdAt" field will only show the creation of the scadatel keypoint
                dateCreated = newValueJSON.getString("updatedAt"),
//                notes = newValueJSON.getString("notes"),
                notes = "" //still no notes field on every field
            )
        }
    }

    //Only to get a date for "Perubahan Spesifikasi (this value)"
    fun getHistoryDateOnly(input: String): String {
        input.let {
            val newValueJSON = JSONObject(it)
            return newValueJSON.getString("updatedAt")
        }
    }
}

