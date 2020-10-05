package com.ramadhan.couriertracking.data.network

import com.ramadhan.couriertracking.data.entity.TrackData
import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.data.network.response.OperationCallback
import com.ramadhan.couriertracking.data.network.rest.ApiClient
import com.ramadhan.couriertracking.utils.ServiceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrackingRemoteRepositoryImpl : TrackingRemoteRepository {
    private var callNew: Call<BaseResponse<TrackData>>? = null

    override fun retrieveTrackingNew(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<TrackData>>
    ) {
        callNew = ApiClient.build()
            .getTrackingNew(awb = awb, api_key = ServiceData.API_KEY, courier = courier)

        callNew?.enqueue(object : Callback<BaseResponse<TrackData>> {
            override fun onResponse(
                call: Call<BaseResponse<TrackData>>,
                response: Response<BaseResponse<TrackData>>
            ) {

                if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                } else {
                    callback.onError(response.body()?.message)
                }
            }

            override fun onFailure(call: Call<BaseResponse<TrackData>>, t: Throwable) {
                callback.onError(t.localizedMessage)
            }

        })
    }

    override fun cancel() {
        callNew?.cancel()
    }
}