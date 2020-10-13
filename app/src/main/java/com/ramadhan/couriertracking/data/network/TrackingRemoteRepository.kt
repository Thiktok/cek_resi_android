package com.ramadhan.couriertracking.data.network

import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.data.entity.TrackData
import com.ramadhan.couriertracking.data.network.response.DataResult

interface TrackingRemoteRepository {

    suspend fun retrieveTrackingNew(
        awb: String,
        courier: String
    ): DataResult<BaseResponse<TrackData>>
}