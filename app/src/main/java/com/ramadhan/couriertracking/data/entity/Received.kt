package com.ramadhan.couriertracking.data.entity

import com.google.gson.annotations.SerializedName

data class Received(
    val addr: String? = null,
    @SerializedName("date")
    val date: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("re")
    val recipient: String? = null
)