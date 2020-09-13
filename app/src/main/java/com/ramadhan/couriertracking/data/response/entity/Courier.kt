package com.ramadhan.couriertracking.data.response.entity

import com.google.gson.annotations.SerializedName

data class Courier(
    val name: String,

    @SerializedName("img_url")
    val imgUrl: String
)