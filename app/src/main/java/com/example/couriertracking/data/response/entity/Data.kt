package com.example.couriertracking.data.response.entity

data class Data<T>(
    val courier: String,
    val received: Received,
    val service: String,
    val shipped: Shipped,
    val status: String,
    val tracking: T,
    val waybill: String
)