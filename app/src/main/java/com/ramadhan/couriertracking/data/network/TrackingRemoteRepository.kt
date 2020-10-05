package com.ramadhan.couriertracking.data.network

import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.data.network.response.OperationCallback
import com.ramadhan.couriertracking.data.entity.Track
import com.ramadhan.couriertracking.data.entity.TrackData
import com.ramadhan.couriertracking.data.entity.Tracking

interface TrackingRemoteRepository {

    fun retrieveTrackingData(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<Track<List<Tracking>>>>
    )

    fun retrieveTrackingNew(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<TrackData>>
    )

    fun cancel()
}