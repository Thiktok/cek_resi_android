package com.example.couriertracking.data

import com.example.couriertracking.data.response.BaseResponse
import com.example.couriertracking.data.response.OperationCallback
import com.example.couriertracking.data.response.entity.Data
import com.example.couriertracking.data.response.entity.Tracking

interface TrackingRepository {

    fun retrieveTrackingData(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<Data<List<Tracking>>>>
    )

    fun cancel()
}