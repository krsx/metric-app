package com.capstone.metricapp.core.utils

import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.core.utils.constans.KeypointsTypeId

fun createIdRandomizer(idType: String): String {
    val randomNumber = (0..99999).random()
    val id = when (idType) {
        KeypointsTypeId.LBS.keypoint -> KeypointsTypeId.LBS.id
        KeypointsTypeId.RECLOSER.keypoint -> KeypointsTypeId.RECLOSER.id
        KeypointsTypeId.GI.keypoint -> KeypointsTypeId.GI.id
        KeypointsTypeId.GH.keypoint -> KeypointsTypeId.GH.id
        KeypointsTypeId.RADIO_DATA.keypoint -> KeypointsTypeId.RADIO_DATA.id
        KeypointsTypeId.RADIO_SUARA.keypoint -> KeypointsTypeId.RADIO_SUARA.id
        KeypointsTypeId.WORK_STATION.keypoint -> KeypointsTypeId.WORK_STATION.id
        KeypointsTypeId.SERVER.keypoint -> KeypointsTypeId.SERVER.id
        KeypointsTypeId.SWITCH.keypoint -> KeypointsTypeId.SWITCH.id
        KeypointsTypeId.GATEWAY.keypoint -> KeypointsTypeId.GATEWAY.id
        KeypointsTypeId.ROUTER.keypoint -> KeypointsTypeId.ROUTER.id
        KeypointsTypeId.GPS.keypoint -> KeypointsTypeId.GPS.id
        else -> {
            KeypointsTypeId.LBS.id
        }
    }

    return id + String.format("%05d", randomNumber)
}

fun extractId(id: String): KeypointsType {
    return if (id.substring(0, 2) == "GI" || id.substring(0, 2) == "GH") {
        KeypointsType.GIGH
    } else if (id.substring(0, 3) == "LBS" || id.substring(0, 3) == "REC") {
        KeypointsType.LBSREC
    } else {
        KeypointsType.SCADATEL
    }
}