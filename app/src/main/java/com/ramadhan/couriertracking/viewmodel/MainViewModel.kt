package com.ramadhan.couriertracking.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramadhan.couriertracking.data.entity.History
import com.ramadhan.couriertracking.data.room.HistoryRepository

class MainViewModel(private val repository: HistoryRepository): ViewModel() {
    val historiesData: LiveData<List<History>> = repository.getHistories()
}