package com.capstone.metricapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KeypointHistory(
    var id: String,
    var documentId: String,
    var modelName: String,
    var fieldName: String,
    var oldValue: String,
    var newValue: String,
) : Parcelable