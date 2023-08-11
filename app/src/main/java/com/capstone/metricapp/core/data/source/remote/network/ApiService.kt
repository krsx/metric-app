package com.capstone.metricapp.core.data.source.remote.network

import com.capstone.metricapp.core.data.source.remote.response.ScadatelItemResponse
import com.capstone.metricapp.core.data.source.remote.response.ScadatelListItemResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("scadatel")
    suspend fun getAllScadatel(): ScadatelListItemResponse

    @GET("scadatel/{id}")
    suspend fun getScadatelById(
        @Path("id") id: String
    ): ScadatelItemResponse
}