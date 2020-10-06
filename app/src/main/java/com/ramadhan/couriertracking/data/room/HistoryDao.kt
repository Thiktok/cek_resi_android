package com.ramadhan.couriertracking.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ramadhan.couriertracking.data.entity.History

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: History)

    @Query("SELECT * FROM search_history WHERE awb = :awb")
    fun getHistory(awb: String): LiveData<History>

    @Query("SELECT * FROM search_history")
    fun getHistories(): LiveData<List<History>>

    @Query("UPDATE search_history SET title = :title WHERE awb = :awb")
    suspend fun changeTitle(awb: String, title: String)

    @Query("DELETE FROM search_history WHERE awb = :awb")
    suspend fun deleteHistory(awb: String)

    @Query("DELETE FROM search_history")
    suspend fun deleteHistories()
}