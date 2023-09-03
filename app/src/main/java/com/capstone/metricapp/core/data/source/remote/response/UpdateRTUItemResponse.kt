package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateRTUResponse(

    @field:SerializedName("data")
    val data: CreateRTUData? = null,

    @field:SerializedName("status")
    val status: String? = null
)
