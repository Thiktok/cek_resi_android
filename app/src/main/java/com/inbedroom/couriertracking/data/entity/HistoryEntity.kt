package com.inbedroom.couriertracking.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = false)
    val awb: String,
    @Embedded(prefix = "courier_")
    val courier: Courier,
    val title: String? = null
)