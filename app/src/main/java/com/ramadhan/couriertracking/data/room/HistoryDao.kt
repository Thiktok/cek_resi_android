package com.ramadhan.couriertracking.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ramadhan.couriertracking.data.entity.History

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(history: History)

    @Query("SELECT * FROM search_history WHERE awb = :awb")
    fun getHistory(awb: String): History

    @Query("SELECT * FROM search_history")
    fun getHistories(): LiveData<List<History>>

    @Query("DELETE FROM search_history WHERE awb = :awb")
    fun deleteHistory(awb: String)

    @Query("DELETE FROM search_history")
    fun deleteHistories()
}