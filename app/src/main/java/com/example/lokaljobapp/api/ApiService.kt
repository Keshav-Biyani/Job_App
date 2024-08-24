package com.example.lokaljobapp.api

import com.example.lokaljobapp.api.response.Job_Response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("common/jobs")
    suspend fun getJobs(@Query("page") page: Int): Response<Job_Response>
}