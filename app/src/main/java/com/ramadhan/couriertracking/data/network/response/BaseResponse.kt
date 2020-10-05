package com.ramadhan.couriertracking.data.network.response

data class BaseResponse<T>(
    val result: Boolean,
    val message: String,
    val data: T?
)