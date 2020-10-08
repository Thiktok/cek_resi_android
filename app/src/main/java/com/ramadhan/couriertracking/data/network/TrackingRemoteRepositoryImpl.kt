package com.ramadhan.couriertracking.data.network

import com.ramadhan.couriertracking.data.entity.TrackData
import com.ramadhan.couriertracking.data.network.api.TrackApi
import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.data.network.response.DataResult
import com.ramadhan.couriertracking.utils.ServiceData
import com.ramadhan.couriertracking.utils.handleApiError
import com.ramadhan.couriertracking.utils.handleApiSuccess
import javax.inject.Inject

class TrackingRemoteRepositoryImpl @Inject constructor(
    private val trackingApi: TrackApi
) : TrackingRemoteRepository {

    override suspend fun retrieveTrackingNew(
        awb: String,
        courier: String
    ): DataResult<BaseResponse<TrackData>> {
        return try {
            val response = trackingApi.getTrackingNew(ServiceData.API_KEY, courier, awb)
            if (response.isSuccessful){
                handleApiSuccess(response.body()!!)
            }else{
                handleApiError(response)
            }
        }catch (e: Exception){
            DataResult.Error(e)
        }
    }
}