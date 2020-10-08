package com.ramadhan.couriertracking.utils

import android.content.Context
import com.ramadhan.couriertracking.data.room.HistoryRepository
import com.ramadhan.couriertracking.data.room.HistoryRepositoryImpl

object Injector {
    private lateinit var historyRepository: HistoryRepository

    fun setup(context: Context){
        historyRepository = HistoryRepositoryImpl(context)
    }

    private fun provideHistoryRepository(): HistoryRepository {
        return historyRepository
    }
}