package com.ramadhan.couriertracking.data.network

import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.data.network.response.OperationCallback
import com.ramadhan.couriertracking.data.entity.Track
import com.ramadhan.couriertracking.data.entity.Tracking
import com.ramadhan.couriertracking.data.network.rest.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrackingRemoteRepositoryImpl : TrackingRemoteRepository {
    private var call: Call<BaseResponse<Track<List<Tracking>>>>? = null

    override fun retrieveTrackingData(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<Track<List<Tracking>>>>
    ) {
        call = ApiClient.build().getTracking(awb = awb, courier = courier)

        call?.enqueue(object : Callback<BaseResponse<Track<List<Tracking>>>> {
            override fun onFailure(call: Call<BaseResponse<Track<List<Tracking>>>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<BaseResponse<Track<List<Tracking>>>>,
                response: Response<BaseResponse<Track<List<Tracking>>>>
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