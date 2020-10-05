package com.ramadhan.couriertracking.data.network.response

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T?
)