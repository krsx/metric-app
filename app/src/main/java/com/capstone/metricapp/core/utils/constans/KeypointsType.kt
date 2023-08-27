package com.capstone.metricapp.core.utils.constans

enum class KeypointsType(val type: String) {
    LBSREC("LBS RECLOSER"),
    GIGH("GI & GH"),
    SCADATEL("SCADATEL"),
}

val keypointsType = listOf("LBS RECLOSER", "GI & GH", "SCADATEL")

fun extractId(id: String): KeypointsType {
    return if (id.substring(0, 2) == "GI" || id.substring(0, 2) == "GH") {
        KeypointsType.GIGH
    } else if (id.substring(0, 3) == "LBS" || id.substring(0, 3) == "REC") {
        KeypointsType.LBSREC
    } else {
        KeypointsType.SCADATEL
    }
}