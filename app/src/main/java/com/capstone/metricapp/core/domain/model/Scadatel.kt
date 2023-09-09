package com.capstone.metricapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Scadatel(
    var id: String,
    var uniqueId: String,
    var keypoint: String,
    var region: String,
    var merk: String,
    var type: String,
    var mainVolt: String,
    var backupVolt: String,
    var os: String,
    var date: String,
    var dateCreated: String,
    var notes: String
) : Parcelable
