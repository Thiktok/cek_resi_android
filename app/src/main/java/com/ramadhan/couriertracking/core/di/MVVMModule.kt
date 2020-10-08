package com.ramadhan.couriertracking.core.di

import androidx.lifecycle.ViewModelProvider
import com.ramadhan.couriertracking.data.network.TrackingRemoteRepository
import com.ramadhan.couriertracking.data.room.HistoryRepository
import com.ramadhan.couriertracking.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MVVMModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(
        trackingRemoteRepository: TrackingRemoteRepository,
        historyRepository: HistoryRepository
    ): ViewModelProvider.Factory = ViewModelFactory(trackingRemoteRepository, historyRepository)
}