package com.ramadhan.couriertracking.data

import com.ramadhan.couriertracking.data.response.BaseResponse
import com.ramadhan.couriertracking.data.response.OperationCallback
import com.ramadhan.couriertracking.data.response.entity.Track
import com.ramadhan.couriertracking.data.response.entity.Tracking

interface TrackingRepository {

    fun retrieveTrackingData(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<Track<List<Tracking>>>>
    )

    fun cancel()
}