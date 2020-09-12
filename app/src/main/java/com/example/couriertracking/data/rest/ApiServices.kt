package com.example.couriertracking.data.rest

import com.example.couriertracking.BuildConfig
import com.example.couriertracking.data.response.BaseResponse
import com.example.couriertracking.data.response.entity.Data
import com.example.couriertracking.data.response.entity.Tracking
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("cekresi?")
    fun getTracking(
        @Query("awb") awb: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("courier") courier: String
    ): Call<BaseResponse<Data<List<Tracking>>>>
}