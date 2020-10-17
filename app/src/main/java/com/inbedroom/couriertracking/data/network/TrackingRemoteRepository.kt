package com.inbedroom.couriertracking.data.network

import com.inbedroom.couriertracking.data.network.response.BaseResponse
import com.inbedroom.couriertracking.data.entity.TrackData
import com.inbedroom.couriertracking.data.network.response.DataResult

interface TrackingRemoteRepository {

    suspend fun retrieveTrackingNew(
        awb: String,
        courier: String
    ): DataResult<BaseResponse<TrackData>>
}