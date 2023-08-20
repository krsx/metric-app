package com.capstone.metricapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LBSREC(
    var id: String,
    var uniqueId: String,
    var keypoint: String,
    var region: String,

    var telkom_merk: String,
    var telkom_type: String,
    var telkom_rangeVolt: String,
    var telkom_date: String,
    var telkom_sn: String,

    var main_sim_provider: String,
    var main_sim_number: String,

    var backup_sim_provider: String,
    var backup_sim_number: String,

    var rtu_merk: String,
    var rtu_type: String,
    var rtu_date: String,
    var rtu_sn: String,

    var bar_merk: String,
    var bat_type: String,
    var bat_date: String,

    var date: String,
    var dateCreated: String,
) : Parcelable
