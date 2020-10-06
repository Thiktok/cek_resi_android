package com.ramadhan.couriertracking.data.room

import androidx.lifecycle.LiveData
import com.ramadhan.couriertracking.data.entity.History

interface HistoryRepository {
    fun getHistories(): LiveData<List<History>>
    fun getHistory(awb: String): LiveData<History>
    suspend fun addHistory(history: History)
    suspend fun changeTitle(awb: String, title: String)
    suspend fun deleteHistory(awb: String)
    suspend fun deleteAll()
}