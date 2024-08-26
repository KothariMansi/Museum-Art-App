package com.example.museumartapp.data

import com.example.museumartapp.domain.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("image")
    fun getArtData(@Query("apikey") apiKey: String): Call<ApiResponse>
}