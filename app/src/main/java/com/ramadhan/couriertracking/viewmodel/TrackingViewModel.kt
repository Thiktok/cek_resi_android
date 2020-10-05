package com.ramadhan.couriertracking.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramadhan.couriertracking.data.entity.*
import com.ramadhan.couriertracking.data.network.TrackingRemoteRepository
import com.ramadhan.couriertracking.data.network.response.BaseResponse
import com.ramadhan.couriertracking.data.network.response.OperationCallback
import com.ramadhan.couriertracking.data.room.HistoryRepository
import kotlinx.coroutines.launch

class TrackingViewModel(private val remoteRepository: TrackingRemoteRepository,
private val historyRepository: HistoryRepository) : ViewModel() {

    private val _trackingData = MutableLiveData<TrackData>()
    val trackingData: LiveData<TrackData> = _trackingData

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _isSuccessful = MutableLiveData<Boolean>()
    val isSuccessful: LiveData<Boolean> = _isSuccessful

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isNoData = MutableLiveData<Boolean>()
    val isNoData: LiveData<Boolean> = _isNoData

    fun saveAsHistory(awb: String, courier: Courier){
        viewModelScope.launch {
            historyRepository.addHistory(History(awb, courier))
        }
    }

    fun getTrackingData(awb: String, courier: String) {
        _isViewLoading.postValue(true)

        remoteRepository.retrieveTrackingNew(
            awb,
            courier,
            object : OperationCallback<BaseResponse<TrackData>> {
                override fun onError(error: String?) {
                    _isViewLoading.postValue(false)
                    _onMessageError.postValue(error)
                }

                override fun onSuccess(data: BaseResponse<TrackData>?) {
                    _isViewLoading.postValue(false)

                    if (data != null) {
                        if (data.status == 200) {
                            _trackingData.postValue(data.data)
                            _isSuccessful.postValue(true)
                        } else {
                            _onMessageError.postValue(data.message)
                        }
                    } else {
                        _isNoData.postValue(true)
                    }
                }


            })
    }
}