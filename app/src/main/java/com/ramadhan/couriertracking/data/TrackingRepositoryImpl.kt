package com.ramadhan.couriertracking.data

import com.ramadhan.couriertracking.data.response.BaseResponse
import com.ramadhan.couriertracking.data.response.OperationCallback
import com.ramadhan.couriertracking.data.response.entity.Data
import com.ramadhan.couriertracking.data.response.entity.Tracking
import com.ramadhan.couriertracking.data.rest.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrackingRepositoryImpl : TrackingRepository {
    private var call: Call<BaseResponse<Data<List<Tracking>>>>? = null

    override fun retrieveTrackingData(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<Data<List<Tracking>>>>
    ) {
        call = ApiClient.build().getTracking(awb = awb, courier = courier)

        call?.enqueue(object : Callback<BaseResponse<Data<List<Tracking>>>> {
            override fun onFailure(call: Call<BaseResponse<Data<List<Tracking>>>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<BaseResponse<Data<List<Tracking>>>>,
                response: Response<BaseResponse<Data<List<Tracking>>>>
            ) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                } else {
                    callback.onError(response.body()?.message)
                }
            }

        })
    }


    override fun cancel() {
        call?.cancel()
    }
}