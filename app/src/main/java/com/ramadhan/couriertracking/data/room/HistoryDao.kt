package com.ramadhan.couriertracking.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ramadhan.couriertracking.data.entity.History

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(history: History)

    @Query("SELECT * FROM search_history WHERE awb = :awb")
    fun getHistory(awb: String): LiveData<History>

    @Delete
    fun deleteHistory(id: Int)
}