package com.ramadhan.couriertracking.data.network

import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.data.network.response.OperationCallback
import com.ramadhan.couriertracking.data.entity.Track
import com.ramadhan.couriertracking.data.entity.TrackData
import com.ramadhan.couriertracking.data.entity.Tracking
import com.ramadhan.couriertracking.data.network.rest.ApiClient
import com.ramadhan.couriertracking.utils.ServiceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrackingRemoteRepositoryImpl : TrackingRemoteRepository {
    private var call: Call<BaseResponse<Track<List<Tracking>>>>? = null
    private var callNew: Call<BaseResponse<TrackData>>? = null

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

    override fun retrieveTrackingNew(
        awb: String,
        courier: String,
        callback: OperationCallback<BaseResponse<TrackData>>
    ) {
        callNew = ApiClient.build().getTrackingNew(awb, ServiceData.API_KEY, courier)

        callNew?.enqueue(object : Callback<BaseResponse<TrackData>>{
            override fun onResponse(
                call: Call<BaseResponse<TrackData>>,
                response: Response<BaseResponse<TrackData>>
            ) {
                if (response.isSuccessful){
                    callback.onSuccess(response.body())
                }else{
                    callback.onError(response.body()?.message)
                }
            }

            override fun onFailure(call: Call<BaseResponse<TrackData>>, t: Throwable) {
                callback.onError(t.localizedMessage)
            }

        })
    }


    override fun cancel() {
        call?.cancel()
        callNew?.cancel()
    }
}