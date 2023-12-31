package com.capstone.metricapp.core.di

import com.capstone.metricapp.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit =
            Retrofit.Builder().baseUrl("https://metric-backend.vercel.app/").client(client)
                .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(ApiService::class.java)
    }
}