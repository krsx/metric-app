package com.capstone.metricapp.core.utils

import com.capstone.metricapp.core.data.source.remote.response.ScadatelData
import com.capstone.metricapp.core.data.source.remote.response.ScadatelItem
import com.capstone.metricapp.core.domain.model.Scadatel

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
                dateCreated = it.createdAt!!
            )
        }

    fun mapScadatelResponseToDomain(input: ScadatelData?): Scadatel = Scadatel(
        id = input?.scadatelItem?.id!!,
        uniqueId = input.scadatelItem.uniqueID!!,
        keypoint = input.scadatelItem.keypoint!!,
        region = input.scadatelItem.region!!,
        merk = input.scadatelItem.merk!!,
        type = input.scadatelItem.type!!,
        mainVolt = input.scadatelItem.mainVolt!!,
        backupVolt = input.scadatelItem.backupVolt!!,
        os = input.scadatelItem.os!!,
        date = input.scadatelItem.date!!,
        dateCreated = input.scadatelItem.createdAt!!
    )
}