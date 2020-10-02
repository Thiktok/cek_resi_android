package com.ramadhan.couriertracking.data.entity

data class Tracking(
    val date: String,
    val desc: String,
    var status: String?= null
)