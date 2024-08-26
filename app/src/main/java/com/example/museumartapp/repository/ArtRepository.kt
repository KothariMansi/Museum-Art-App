package com.example.museumartapp.repository

import com.example.museumartapp.ApiKey.API_KEY
import com.example.museumartapp.data.ApiService
import com.example.museumartapp.domain.models.ApiResponse
import retrofit2.Call
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val apiService: ApiService
) {
     fun getArtData(): Call<ApiResponse> {
         return apiService.getArtData(API_KEY)
     }
}