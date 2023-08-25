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
    ): ScadatelItemResponse

    @PUT("scadatel{id}")
    @FormUrlEncoded
    suspend fun updateSpecScadatel(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
        @Field("uniqueID") uniqueID: String,
        @Field("keypoint") keypoint: String,
        @Field("lokasi") region: String,
        @Field("merk") merk: String,
        @Field("tipe") type: String,
        @Field("mainVolt") mainVolt: String,
        @Field("backupVolt") backupVolt: String,
        @Field("os") os: String,
        @Field("tanggalPemasangan") date: String,
    ): ScadatelItemResponse

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

    @DELETE
    suspend fun deleteRTUKeypoint(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
    ): CommonResponse
}