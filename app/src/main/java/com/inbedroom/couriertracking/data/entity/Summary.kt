package com.inbedroom.couriertracking.data.entity

data class Summary(
    val awb: String,
    val courier: String,
    val service: String? = null,
    val status: String? = null,
    val date: String? = null
)