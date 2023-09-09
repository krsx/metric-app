package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RTUItemResponse(

    @field:SerializedName("data")
    val data: RTUData? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class RTUItem(

    @field:SerializedName("rtu_tipe")
    val rtuType: String? = null,

    @field:SerializedName("rtu_sn")
    val rtuSn: String? = null,

    @field:SerializedName("btr_tanggalPenggatian")
    val batDate: String? = null,

    @field:SerializedName("providerSimCadangan")
    val backupSimProvider: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("gtwy_sn")
    val gatSn: String? = null,

    @field:SerializedName("btr_tipe")
    val batType: String? = null,

    @field:SerializedName("__v")
    val v: Int? = null,

    @field:SerializedName("rect_tipe")
    val rectType: String? = null,

    @field:SerializedName("keypoint")
    val keypoint: String? = null,

    @field:SerializedName("rect_tanggalPenggantian")
    val rectDate: String? = null,

    @field:SerializedName("gtwy_tipe")
    val gatType: String? = null,

    @field:SerializedName("uniqueID")
    val uniqueID: String? = null,

    @field:SerializedName("rect_sn")
    val rectSn: String? = null,

    @field:SerializedName("nomorSimCadangan")
    val backupSimNumber: String? = null,

    @field:SerializedName("telkom_tipe")
    val telkomType: String? = null,

    @field:SerializedName("telkom_tanggalPenggantian")
    val telkomDate: String? = null,

    @field:SerializedName("gtwy_tanggalPenggatian")
    val gatDate: String? = null,

    @field:SerializedName("btr_merk")
    val batMerk: String? = null,

    @field:SerializedName("rtu_merk")
    val rtuMerk: String? = null,

    @field:SerializedName("telkom_sn")
    val telkomSn: String? = null,

    @field:SerializedName("rtu_tanggalPenggatian")
    val rtuDate: String? = null,

    @field:SerializedName("rect_merk")
    val rectMerk: String? = null,

    @field:SerializedName("lokasi")
    val region: String? = null,

    @field:SerializedName("telkom_rangeVolt")
    val telkomRangeVolt: String? = null,

    @field:SerializedName("nomorSimUtama")
    val mainSimProvider: String? = null,

    @field:SerializedName("telkom_merk")
    val telkomMerk: String? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("rect_rangeVolt")
    val rectRangeVolt: String? = null,

    @field:SerializedName("providerSimUtama")
    val mainSimNumber: String? = null,

    @field:SerializedName("gtwy_merk")
    val gatMerk: String? = null,

    @field:SerializedName("notes")
    val notes: String? = null
)

data class RTUData(

    @field:SerializedName("rtuItem")
    val rtuItem: RTUItem? = null
)
