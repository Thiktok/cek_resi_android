package com.ramadhan.couriertracking.data.room

import android.content.Context
import androidx.lifecycle.LiveData
import com.ramadhan.couriertracking.data.entity.History

class HistoryRepositoryImpl(context: Context) : HistoryRepository {
    private var historyDao: HistoryDao

    init {
        val db = HistoryDatabase.invoke(context)
        db.let {
            historyDao = it.historyDao()
        }
    }

    override fun getHistories(): LiveData<List<History>> {
        return historyDao.getHistories()
    }

    override fun getHistory(awb: String): History {
        return historyDao.getHistory(awb)
    }

    override suspend fun addHistory(history: History) {
        return historyDao.insert(history)
    }

    override suspend fun deleteHistory(awb: String) {
        return historyDao.deleteHistory(awb)
    }

    override suspend fun deleteAll() {
        return historyDao.deleteHistories()
    }

}