package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RTUListItemResponse(

    @field:SerializedName("data")
    val data: RTUListData? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class RTUListData(

    @field:SerializedName("item")
    val item: List<RTUItem?>? = null
)

