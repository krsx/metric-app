package com.capstone.metricapp.core.data.source.remote.network

import com.capstone.metricapp.core.data.source.remote.response.*
import retrofit2.http.*

interface ApiService {
    //USER
    @POST("authentication/signin")
    @FormUrlEncoded
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @POST("authentication/signup")
    @FormUrlEncoded
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("divisi") division: String
    ): RegisterResponse

    //SCADATEL
    @GET("scadatel")
    suspend fun getAllScadatel(
        @Header("Authorization") authorization: String,
    ): ScadatelListItemResponse

    @GET("scadatel/{id}")
    suspend fun getScadatelById(
        @Header("Authorization") authorization: String,
        @Path("id") id: String
    ): ScadatelItemResponse

    @GET("scadatel")
    suspend fun findScadatelKeypoint(
        @Header("Authorization") authorization: String,
        @Query("keyword") keyword: String,
    ): ScadatelListItemResponse

    @POST("scadatel")
    @FormUrlEncoded
    suspend fun createScadatelKeypoint(
        @Header("Authorization") authorization: String,
        @Field("uniqueID") uniqueID: String,
        @Field("keypoint") keypoint: String,
        @Field("lokasi") region: String,
        @Field("merk") merk: String,
        @Field("tipe") type: String,
        @Field("mainVolt") mainVolt: String,
        @Field("backupVolt") backupVolt: String,
        @Field("os") os: String,
        @Field("tanggalPemasangan") date: String,
    ): CreateScadatelItemResponse

    @PUT("scadatel/{id}")
    @FormUrlEncoded
    suspend fun updateSpecScadatel(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
        @Field("uniqueID") uniqueID: String,
        @Field("merk") merk: String,
        @Field("tipe") type: String,
        @Field("mainVolt") mainVolt: String,
        @Field("backupVolt") backupVolt: String,
        @Field("os") os: String,
        @Field("tanggalPemasangan") date: String,
    ): UpdateScadatelItemResponse

    @GET("scadatel/history/{id}")
    suspend fun getScadatelHistory(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
    ): KeypointHistoryResponse

    @DELETE
    suspend fun deleteScadatelKeypoint(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
    ): CommonResponse

    //RTU
    @GET("rtu")
    suspend fun getAllRTU(
        @Header("Authorization") authorization: String,
    ): RTUListItemResponse

    @GET("rtu/{id}")
    suspend fun getRTUById(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
    ): RTUItemResponse

    @GET("rtu")
    suspend fun findRTUKeypoint(
        @Header("Authorization") authorization: String,
        @Query("keyword") keyword: String,
    ): RTUListItemResponse

    @POST("rtu")
    @FormUrlEncoded
    suspend fun createLBSRECKeypoint(
        @Header("Authorization") authorization: String,
        @Field("uniqueID") uniqueID: String,
        @Field("keypoint") keypoint: String,
        @Field("lokasi") region: String,

        @Field("telkom_merk") telkom_merk: String,
        @Field("telkom_tipe") telkom_type: String,
        @Field("telkom_rangeVolt") telkom_rangeVolt: String,
        @Field("telkom_tanggalPenggantian") telkom_date: String,
        @Field("telkom_sn") telkom_sn: String,

        @Field("providerSimUtama") main_sim_provider: String,
        @Field("nomorSimUtama") main_sim_number: String,
        @Field("providerSimCadangan") backup_sim_provider: String,
        @Field("nomorSimCadangan") backup_sim_number: String,

        @Field("rtu_merk") rtu_merk: String,
        @Field("rtu_tipe") rtu_type: String,
        @Field("rtu_tanggalPenggatian") rtu_date: String,
        @Field("rtu_sn") rtu_sn: String,

        @Field("btr_merk") bat_merk: String,
        @Field("btr_tipe") bat_type: String,
        @Field("btr_tanggalPenggatian") bat_date: String,
    ): CreateRTUItemResponse

    @POST("rtu")
    @FormUrlEncoded
    suspend fun createGIGHKeypoint(
        @Header("Authorization") authorization: String,
        @Field("uniqueID") uniqueID: String,
        @Field("keypoint") keypoint: String,
        @Field("lokasi") region: String,

        @Field("telkom_merk") telkom_merk: String,
        @Field("telkom_tipe") telkom_type: String,
        @Field("telkom_rangeVolt") telkom_rangeVolt: String,
        @Field("telkom_tanggalPenggantian") telkom_date: String,
        @Field("telkom_sn") telkom_sn: String,

        @Field("rect_merk") rect_merk: String,
        @Field("rect_tipe") rect_type: String,
        @Field("rect_rangeVolt") rect_rangeVolt: String,
        @Field("rect_tanggalPenggantian") rect_date: String,
        @Field("rect_sn") rect_sn: String,

        @Field("rtu_merk") rtu_merk: String,
        @Field("rtu_tipe") rtu_type: String,
        @Field("rtu_tanggalPenggatian") rtu_date: String,
        @Field("rtu_sn") rtu_sn: String,

        @Field("btr_merk") bat_merk: String,
        @Field("btr_tipe") bat_type: String,
        @Field("btr_tanggalPenggatian") bat_date: String,

        @Field("gtwy_merk") gat_merk: String,
        @Field("gtwy_tipe") gat_type: String,
        @Field("gtwy_tanggalPenggatian") gat_date: String,
        @Field("gtwy_sn") gat_sn: String,
    ): CreateRTUItemResponse

    @PUT("rtu/{id}")
    @FormUrlEncoded
    suspend fun updateSpecLBSREC(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
        @Field("uniqueID") uniqueID: String,

        @Field("telkom_merk") telkom_merk: String,
        @Field("telkom_tipe") telkom_type: String,
        @Field("telkom_rangeVolt") telkom_rangeVolt: String,
        @Field("telkom_tanggalPenggantian") telkom_date: String,
        @Field("telkom_sn") telkom_sn: String,

        @Field("providerSimUtama") main_sim_provider: String,
        @Field("nomorSimUtama") main_sim_number: String,
        @Field("providerSimCadangan") backup_sim_provider: String,
        @Field("nomorSimCadangan") backup_sim_number: String,

        @Field("rtu_merk") rtu_merk: String,
        @Field("rtu_tipe") rtu_type: String,
        @Field("rtu_tanggalPenggatian") rtu_date: String,
        @Field("rtu_sn") rtu_sn: String,

        @Field("btr_merk") bat_merk: String,
        @Field("btr_tipe") bat_type: String,
        @Field("btr_tanggalPenggatian") bat_date: String,
    ): UpdateRTUResponse

    @PUT("rtu/{id}")
    @FormUrlEncoded
    fun updateSpecGIGH(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
        @Field("uniqueID") uniqueID: String,

        @Field("telkom_merk") telkom_merk: String,
        @Field("telkom_tipe") telkom_type: String,
        @Field("telkom_rangeVolt") telkom_rangeVolt: String,
        @Field("telkom_tanggalPenggantian") telkom_date: String,
        @Field("telkom_sn") telkom_sn: String,

        @Field("rect_merk") rect_merk: String,
        @Field("rect_tipe") rect_type: String,
        @Field("rect_rangeVolt") rect_rangeVolt: String,
        @Field("rect_tanggalPenggantian") rect_date: String,
        @Field("rect_sn") rect_sn: String,

        @Field("rtu_merk") rtu_merk: String,
        @Field("rtu_tipe") rtu_type: String,
        @Field("rtu_tanggalPenggatian") rtu_date: String,
        @Field("rtu_sn") rtu_sn: String,

        @Field("btr_merk") bat_merk: String,
        @Field("btr_tipe") bat_type: String,
        @Field("btr_tanggalPenggatian") bat_date: String,

        @Field("gtwy_merk") gat_merk: String,
        @Field("gtwy_tipe") gat_type: String,
        @Field("gtwy_tanggalPenggatian") gat_date: String,
        @Field("gtwy_sn") gat_sn: String,
    ): UpdateRTUResponse

    @GET("rtu/history/{id}")
    suspend fun getRTUHistory(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
    ): KeypointHistoryResponse

    @DELETE("rtu/{id}")
    suspend fun deleteRTUKeypoint(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
    ): CommonResponse
}