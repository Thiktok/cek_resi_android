package com.ramadhan.couriertracking.data.response.entity

data class Received(
    val addr: String,
    val date: String,
    val name: String,
    val recipient: String
)