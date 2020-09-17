package com.ramadhan.couriertracking.data.response.entity

data class Track<T>(
    val courier: String,
    val received: Received,
    val service: String,
    val shipped: Shipped,
    val status: String,
    val tracking: T,
    val waybill: String
)