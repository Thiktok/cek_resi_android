package com.ramadhan.couriertracking.data

import com.ramadhan.couriertracking.data.response.BaseResponse
import com.ramadhan.couriertracking.data.response.OperationCallback
import com.ramadhan.couriertracking.data.response.entity.Data
import com.ramadhan.couriertracking.data.response.entity.Tracking

interface TrackingRepository {

    fun retrieveTrackingData(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<Data<List<Tracking>>>>
    )

    fun cancel()
}