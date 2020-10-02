package com.ramadhan.couriertracking.data.entity

import com.google.gson.annotations.SerializedName

data class Detail(
    val status: String,
    val service: String,
    val date: String,
    @SerializedName("shipper_name")
    val shipper: String,
    val origin: String,
    @SerializedName("receiver_name")
    val receiver: String,
    val destination: String
)