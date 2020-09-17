package com.ramadhan.couriertracking.utils

import androidx.lifecycle.ViewModelProvider
import com.ramadhan.couriertracking.data.TrackingRepository
import com.ramadhan.couriertracking.data.TrackingRepositoryImpl
import com.ramadhan.couriertracking.viewmodel.ViewModelFactory

object Injector {
    private val trackingRepository: TrackingRepository = TrackingRepositoryImpl()
    private val viewModelProvider = ViewModelFactory(trackingRepository)

    fun provideRepository(): TrackingRepository{
        return trackingRepository
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return viewModelProvider
    }
}