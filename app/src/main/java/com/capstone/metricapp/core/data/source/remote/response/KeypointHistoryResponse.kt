package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class KeypointHistoryResponse(

    @field:SerializedName("data")
    val data: KeypointHistoryData? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class KeypointHistoryItem(

    @field:SerializedName("modelName")
    val modelName: String? = null,

    @field:SerializedName("newValue")
    val newValue: String? = null,

    @field:SerializedName("fieldName")
    val fieldName: String? = null,

    @field:SerializedName("__v")
    val v: Int? = null,

    @field:SerializedName("documentId")
    val documentId: String? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("oldValue")
    val oldValue: String? = null,

    @field:SerializedName("timestamp")
    val timestamp: String? = null
)

data class KeypointHistoryData(

    @field:SerializedName("allHistory")
    val allHistory: List<KeypointHistoryItem?>? = null
)
