package com.ramadhan.couriertracking.data.entity

import com.google.gson.annotations.SerializedName

data class Track<T>(
    @SerializedName("courier")
    val courier: String,
    @SerializedName("received")
    val received: Received? = null,
    @SerializedName("detail")
    var detail: Detail? = null,
    @SerializedName("service")
    var service: String? = null,
    @SerializedName("shipped")
    var shipped: Shipped ? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("tracking")
    val tracking: T,
    @SerializedName("waybill", alternate = ["awb"])
    val waybill: String
)