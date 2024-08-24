package com.example.lokaljobapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL="https://testapi.getlokalapp.com/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}