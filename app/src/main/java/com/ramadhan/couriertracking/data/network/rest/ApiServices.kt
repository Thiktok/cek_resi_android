package com.ramadhan.couriertracking.data.network.rest

import com.ramadhan.couriertracking.data.entity.Track
import com.ramadhan.couriertracking.data.entity.Tracking
import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.utils.ServiceData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("cekresi?")
    fun getTracking(
        @Query("awb") awb: String,
        @Query("api_key") api_key: String = ServiceData.API_KEY,
        @Query("courier") courier: String
    ): Call<BaseResponse<Track<List<Tracking>>>>
}