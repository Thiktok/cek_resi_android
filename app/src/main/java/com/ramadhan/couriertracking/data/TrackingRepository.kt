package com.ramadhan.couriertracking.data

import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.data.network.response.OperationCallback
import com.ramadhan.couriertracking.data.entity.Track
import com.ramadhan.couriertracking.data.entity.Tracking

interface TrackingRepository {

    fun retrieveTrackingData(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<Track<List<Tracking>>>>
    )

    fun cancel()
}