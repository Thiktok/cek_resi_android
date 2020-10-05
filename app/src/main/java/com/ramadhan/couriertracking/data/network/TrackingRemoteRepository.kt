package com.ramadhan.couriertracking.data.network

import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.data.network.response.OperationCallback
import com.ramadhan.couriertracking.data.entity.TrackData

interface TrackingRemoteRepository {

    fun retrieveTrackingNew(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<TrackData>>
    )

    fun cancel()
}