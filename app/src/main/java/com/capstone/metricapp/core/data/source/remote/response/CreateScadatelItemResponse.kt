package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreateScadatelItemResponse(

    @field:SerializedName("data")
    val data: CreateScadatelData? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class CreateScadatelData(

    @field:SerializedName("item")
    val item: ScadatelItem? = null
)
