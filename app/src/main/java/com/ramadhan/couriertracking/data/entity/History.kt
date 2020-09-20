package com.ramadhan.couriertracking.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class History(
    @PrimaryKey(autoGenerate = false)
    val awb: String,
    @Embedded(prefix = "courier_")
    val courier: Courier,
    val title: String? = null
)