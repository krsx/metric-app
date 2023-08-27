package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateScadatelItemResponse(

    @field:SerializedName("data")
    val data: ScadatelData? = null,

    @field:SerializedName("status")
    val status: String? = null
)


