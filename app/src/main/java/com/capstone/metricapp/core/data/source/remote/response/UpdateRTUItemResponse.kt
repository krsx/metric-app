package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateRTUResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("rtu_tipe")
	val rtuTipe: String? = null,

	@field:SerializedName("rtu_sn")
	val rtuSn: String? = null,

	@field:SerializedName("btr_tanggalPenggatian")
	val btrTanggalPenggatian: String? = null,

	@field:SerializedName("providerSimCadangan")
	val providerSimCadangan: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("gtwy_sn")
	val gtwySn: String? = null,

	@field:SerializedName("btr_tipe")
	val btrTipe: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("rect_tipe")
	val rectTipe: String? = null,

	@field:SerializedName("keypoint")
	val keypoint: String? = null,

	@field:SerializedName("rect_tanggalPenggantian")
	val rectTanggalPenggantian: String? = null,

	@field:SerializedName("gtwy_tipe")
	val gtwyTipe: String? = null,

	@field:SerializedName("uniqueID")
	val uniqueID: String? = null,

	@field:SerializedName("rect_sn")
	val rectSn: String? = null,

	@field:SerializedName("nomorSimCadangan")
	val nomorSimCadangan: String? = null,

	@field:SerializedName("telkom_tipe")
	val telkomTipe: String? = null,

	@field:SerializedName("telkom_tanggalPenggantian")
	val telkomTanggalPenggantian: String? = null,

	@field:SerializedName("gtwy_tanggalPenggatian")
	val gtwyTanggalPenggatian: String? = null,

	@field:SerializedName("btr_merk")
	val btrMerk: String? = null,

	@field:SerializedName("rtu_merk")
	val rtuMerk: String? = null,

	@field:SerializedName("telkom_sn")
	val telkomSn: String? = null,

	@field:SerializedName("rtu_tanggalPenggatian")
	val rtuTanggalPenggatian: String? = null,

	@field:SerializedName("rect_merk")
	val rectMerk: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("telkom_rangeVolt")
	val telkomRangeVolt: String? = null,

	@field:SerializedName("nomorSimUtama")
	val nomorSimUtama: String? = null,

	@field:SerializedName("telkom_merk")
	val telkomMerk: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("rect_rangeVolt")
	val rectRangeVolt: String? = null,

	@field:SerializedName("providerSimUtama")
	val providerSimUtama: String? = null,

	@field:SerializedName("gtwy_merk")
	val gtwyMerk: String? = null
)
