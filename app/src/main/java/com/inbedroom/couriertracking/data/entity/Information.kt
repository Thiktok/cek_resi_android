package com.inbedroom.couriertracking.data.entity

data class Information(
    val origin: String? = null,
    val destination: String,
    val shipper: String? = null,
    val receiver: String
)