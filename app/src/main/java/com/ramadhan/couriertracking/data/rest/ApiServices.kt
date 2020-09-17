package com.ramadhan.couriertracking.data.rest

import com.ramadhan.couriertracking.BuildConfig
import com.ramadhan.couriertracking.data.response.BaseResponse
import com.ramadhan.couriertracking.data.response.entity.Track
import com.ramadhan.couriertracking.data.response.entity.Tracking
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("cekresi?")
    fun getTracking(
        @Query("awb") awb: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("courier") courier: String
    ): Call<BaseResponse<Track<List<Tracking>>>>
}