package com.inbedroom.couriertracking.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.inbedroom.couriertracking.data.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(history: HistoryEntity)

    @Query("SELECT * FROM search_history WHERE awb = :awb")
    fun getHistory(awb: String): LiveData<HistoryEntity>

    @Query("SELECT * FROM search_history")
    fun getHistories(): LiveData<List<HistoryEntity>>

    @Query("UPDATE search_history SET title = :title WHERE awb = :awb")
    suspend fun changeTitle(awb: String, title: String)

    @Query("DELETE FROM search_history WHERE awb = :awb")
    suspend fun deleteHistory(awb: String)

    @Query("DELETE FROM search_history")
    suspend fun deleteHistories()
}