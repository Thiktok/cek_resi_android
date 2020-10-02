package com.ramadhan.couriertracking.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramadhan.couriertracking.data.entity.History
import com.ramadhan.couriertracking.data.room.HistoryRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: HistoryRepository): ViewModel() {

    val historiesData: LiveData<List<History>> = repository.getHistories()

    fun deleteHistory(awb: String){
        viewModelScope.launch {
            repository.deleteHistory(awb)
        }
    }
}