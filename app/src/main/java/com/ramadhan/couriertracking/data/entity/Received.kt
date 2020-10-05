package com.ramadhan.couriertracking.data.entity

import com.google.gson.annotations.SerializedName

data class Received(
    @SerializedName("addr", alternate = ["address"])
    val addr: String? = null,
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("recipient")
    val recipient: String? = null
)