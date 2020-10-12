package com.ramadhan.couriertracking.data.room

import android.content.Context
import androidx.lifecycle.LiveData
import com.ramadhan.couriertracking.data.entity.HistoryEntity
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(context: Context) : HistoryRepository {
    private var historyDao: HistoryDao

    init {
        val db = HistoryDatabase.invoke(context)
        db.let {
            historyDao = it.historyDao()
        }
    }

    override fun getHistories(): LiveData<List<HistoryEntity>> {
        return historyDao.getHistories()
    }

    override fun getHistory(awb: String): LiveData<HistoryEntity> {
        return historyDao.getHistory(awb)
    }

    override suspend fun addHistory(history: HistoryEntity) {
        historyDao.insert(history)
    }

    override suspend fun changeTitle(awb: String, title: String){
        historyDao.changeTitle(awb, title)
    }

    override suspend fun deleteHistory(awb: String) {
        historyDao.deleteHistory(awb)
    }

    override suspend fun deleteAll() {
        historyDao.deleteHistories()
    }

}