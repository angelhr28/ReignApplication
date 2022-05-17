package com.example.reignapplication.data.network

import com.example.reignapplication.data.model.ResponseNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HitApiClient {
    @GET("search_by_date?")
    suspend fun getAllHits(
        @Query("query") query: String = "mobile",
    ): Response<ResponseNews>
}