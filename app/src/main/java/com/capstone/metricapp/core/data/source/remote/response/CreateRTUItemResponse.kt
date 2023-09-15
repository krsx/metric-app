package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreateRTUItemResponse(

    @field:SerializedName("data")
    val data: CreateRTUData? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class CreateRTUData(

    @field:SerializedName("uniqueID")
    val uniqueID: String? = null,

    @field:SerializedName("keypoint")
    val keypoint: String? = null,

    @field:SerializedName("lokasi")
    val region: String? = null,

    @field:SerializedName("telkom_merk")
    val telkom_merk: String? = null,

    @field:SerializedName("telkom_tipe")
    val telkom_type: String? = null,

    @field:SerializedName("telkom_rangeVolt")
    val telkom_rangeVolt: String? = null,

    @field:SerializedName("telkom_tanggalPenggantian")
    val telkom_date: String? = null,

    @field:SerializedName("telkom_sn")
    val telkom_sn: String? = null,

    @field:SerializedName("providerSimUtama")
    val main_sim_provider: String? = null,

    @field:SerializedName("nomorSimUtama")
    val main_sim_number: String? = null,

    @field:SerializedName("providerSimCadangan")
    val backup_sim_provider: String? = null,

    @field:SerializedName("nomorSimCadangan")
    val backup_sim_number: String? = null,

    @field:SerializedName("rtu_merk")
    val rtu_merk: String? = null,

    @field:SerializedName("rtu_tipe")
    val rtu_type: String? = null,

    @field:SerializedName("rtu_tanggalPenggatian")
    val rtu_date: String? = null,

    @field:SerializedName("rtu_sn")
    val rtu_sn: String? = null,

    @field:SerializedName("btr_merk")
    val bat_merk: String? = null,

    @field:SerializedName("btr_tipe")
    val bat_type: String? = null,

    @field:SerializedName("btr_tanggalPenggatian")
    val bat_date: String? = null,

    @field:SerializedName("rect_merk")
    val rect_merk: String? = null,

    @field:SerializedName("rect_tipe")
    val rect_type: String? = null,

    @field:SerializedName("rect_rangeVolt")
    val rect_rangeVolt: String? = null,

    @field:SerializedName("rect_tanggalPenggantian")
    val rect_date: String? = null,

    @field:SerializedName("rect_sn")
    val rect_sn: String? = null,

    @field:SerializedName("gtwy_merk")
    val gat_merk: String? = null,

    @field:SerializedName("gtwy_tipe")
    val gat_type: String? = null,

    @field:SerializedName("gtwy_tanggalPenggatian")
    val gat_date: String? = null,

    @field:SerializedName("gtwy_sn")
    val gat_sn: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("__v")
    val v: Int? = null,

    @field:SerializedName("notes")
    val notes: String? = null,

    @field:SerializedName("device")
    val device: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)
