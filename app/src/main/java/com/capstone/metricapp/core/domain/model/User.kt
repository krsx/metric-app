package com.capstone.metricapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val email: String,
    val password: String,
    val division: String,
    val createdAt: String,
    val token:String,
):Parcelable
