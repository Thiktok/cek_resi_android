package com.ramadhan.couriertracking.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.ramadhan.couriertracking.data.network.TrackingRemoteRepository
import com.ramadhan.couriertracking.data.network.TrackingRemoteRepositoryImpl
import com.ramadhan.couriertracking.data.room.HistoryDao
import com.ramadhan.couriertracking.data.room.HistoryDatabase
import com.ramadhan.couriertracking.data.room.HistoryRepository
import com.ramadhan.couriertracking.data.room.HistoryRepositoryImpl
import com.ramadhan.couriertracking.viewmodel.ViewModelFactory

object Injector {
    private val trackingRemoteRepository: TrackingRemoteRepository = TrackingRemoteRepositoryImpl()
    private val viewModelProvider = ViewModelFactory(trackingRemoteRepository, historyRepository)

    private lateinit var historyRepository: HistoryRepository

    fun setup(context: Context){
        historyRepository = HistoryRepositoryImpl(context)
    }

    fun provideRemoteRepository(): TrackingRemoteRepository {
        return trackingRemoteRepository
    }

    fun provideHistoryRepository(): HistoryRepository {
        return historyRepository
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return viewModelProvider
    }
}