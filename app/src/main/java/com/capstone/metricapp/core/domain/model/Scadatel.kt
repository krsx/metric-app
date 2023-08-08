package com.capstone.metricapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Scadatel(
    val id: String,
    val uniqueId: String,
    val keypoint: String,
    val region: String,
    val merk: String,
    val type: String,
    val mainVolt: String,
    val backupVolt: String,
    val os: String,
    val date: String,
    val dateCreated: String,
) : Parcelable
