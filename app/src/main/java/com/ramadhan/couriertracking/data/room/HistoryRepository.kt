package com.ramadhan.couriertracking.data.room

import androidx.lifecycle.LiveData
import com.ramadhan.couriertracking.data.entity.HistoryEntity

interface HistoryRepository {
    fun getHistories(): LiveData<List<HistoryEntity>>
    fun getHistory(awb: String): LiveData<HistoryEntity>
    suspend fun addHistory(history: HistoryEntity)
    suspend fun changeTitle(awb: String, title: String)
    suspend fun deleteHistory(awb: String)
    suspend fun deleteAll()
}