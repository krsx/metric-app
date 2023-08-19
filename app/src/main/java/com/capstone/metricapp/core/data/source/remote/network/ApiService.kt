package com.capstone.metricapp.core.data.source.remote.network

import com.capstone.metricapp.core.data.source.remote.response.LoginResponse
import com.capstone.metricapp.core.data.source.remote.response.RegisterResponse
import com.capstone.metricapp.core.data.source.remote.response.ScadatelItemResponse
import com.capstone.metricapp.core.data.source.remote.response.ScadatelListItemResponse
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
}