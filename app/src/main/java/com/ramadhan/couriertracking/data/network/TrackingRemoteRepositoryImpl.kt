package com.ramadhan.couriertracking.data.network

import android.util.Log
import com.ramadhan.couriertracking.data.entity.TrackData
import com.ramadhan.couriertracking.data.network.api.TrackApi
import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.data.network.response.DataResult
import com.ramadhan.couriertracking.utils.ServiceData
import com.ramadhan.couriertracking.utils.handleApiError
import com.ramadhan.couriertracking.utils.handleApiSuccess
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TrackingRemoteRepositoryImpl @Inject constructor(
    private val trackingApi: TrackApi
) : TrackingRemoteRepository {

    override suspend fun retrieveTrackingNew(
        awb: String,
        courier: String
    ): DataResult<BaseResponse<TrackData>> {
        var result: DataResult<BaseResponse<TrackData>> = DataResult.Empty
        trackingApi
            .getTrackingNew(awb = awb, api_key = ServiceData.API_KEY, courier = courier)
            .enqueue(object : Callback<BaseResponse<TrackData>> {
                override fun onResponse(
                    call: Call<BaseResponse<TrackData>>,
                    response: Response<BaseResponse<TrackData>>
                ) {
                    result = if (response.isSuccessful) {
                        Log.d("callback repo", "success")
                        handleApiSuccess(response.body()!!)
                    } else {
                        handleApiError(response)
                    }
                }

                override fun onFailure(call: Call<BaseResponse<TrackData>>, t: Throwable) {
                    result = DataResult.Error(Exception(t))
                }
            })

        return result
    }
}