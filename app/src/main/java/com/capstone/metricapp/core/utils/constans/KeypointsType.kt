package com.capstone.metricapp.core.utils.constans

enum class KeypointsType(val type: String) {
    LBSREC("LBS RECLOSER"),
    GIGH("GI & GH"),
    SCADATEL("SCADATEL"),
}


val keypointsType = listOf("LBS RECLOSER", "GI & GH", "SCADATEL")

enum class KeypointsTypeId(val keypoint: String, val id: String) {
    LBS("LBS", "LBS"),
    RECLOSER("RECLOSER", "REC"),
    GH("GH", "GH"),
    GI("GI", "GI"),
    RADIO_DATA("RADIO DATA", "RDD"),
    RADIO_SUARA("RADIO SUARA", "RDS"),
    WORK_STATION("WORK STATION", "WS"),
    SERVER("SERVER", "SVR"),
    SWITCH("SWITCH", "S"),
    GATEWAY("GATEWAY", "GW"),
    ROUTER("ROUTER", "R"),
    GPS("GPS", "GPS"),
}

val keypointsIdType = listOf(
    "LBS",
    "RECLOSER",
    "GH",
    "GI",
    "RADIO DATA",
    "RADIO SUARA",
    "WORK STATION",
    "SERVER",
    "SWITCH",
    "GATEWAY",
    "ROUTER",
    "SWITCH",
    "GPS"
)
