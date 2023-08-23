package com.capstone.metricapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Common(
    var status: String,
    var message: String,
) : Parcelable