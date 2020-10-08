package com.ramadhan.couriertracking.data.network.api

import com.ramadhan.couriertracking.data.entity.TrackData
import com.ramadhan.couriertracking.data.network.response.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackApi {

    @GET("track")
    fun getTrackingNew(
        @Query("api_key") api_key: String,
        @Query("courier") courier: String,
        @Query("awb") awb: String
    ): Call<BaseResponse<TrackData>>
}