package com.ramadhan.couriertracking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ramadhan.couriertracking.data.TrackingRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: TrackingRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrackingViewModel(repository) as T
    }
}