package com.capstone.metricapp.core.data.source.remote.network

import com.capstone.metricapp.core.data.source.remote.response.LoginResponse
import com.capstone.metricapp.core.data.source.remote.response.RegisterResponse
import com.capstone.metricapp.core.data.source.remote.response.ScadatelItemResponse
import com.capstone.metricapp.core.data.source.remote.response.ScadatelListItemResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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
    suspend fun getAllScadatel(): ScadatelListItemResponse

    @GET("scadatel/{id}")
    suspend fun getScadatelById(
        @Path("id") id: String
    ): ScadatelItemResponse

}