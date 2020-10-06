package com.ramadhan.couriertracking.data.network.response

interface OperationCallback<T> {
    fun onSuccess(data: T?)
    fun onError(code: Int = 999, errorMessage: String?)
}