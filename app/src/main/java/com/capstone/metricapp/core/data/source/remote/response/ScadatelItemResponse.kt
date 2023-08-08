package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScadatelItemResponse(

    @field:SerializedName("data")
    val data: ScadatelData? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class ScadatelItem(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("merk")
    val merk: String? = null,

    @field:SerializedName("tanggalPemasangan")
    val date: String? = null,

    @field:SerializedName("os")
    val os: String? = null,

    @field:SerializedName("lokasi")
    val region: String? = null,

    @field:SerializedName("backupVolt")
    val backupVolt: String? = null,

    @field:SerializedName("__v")
    val v: Int? = null,

    @field:SerializedName("keypoint")
    val keypoint: String? = null,

    @field:SerializedName("mainVolt")
    val mainVolt: String? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("tipe")
    val type: String? = null,

    @field:SerializedName("uniqueID")
    val uniqueID: String? = null
)

data class ScadatelData(

    @field:SerializedName("scadatelItem")
    val scadatelItem: ScadatelItem? = null
)
