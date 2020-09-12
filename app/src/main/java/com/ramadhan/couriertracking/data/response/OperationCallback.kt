package com.ramadhan.couriertracking.data.response

interface OperationCallback<T> {
    fun onSuccess(data: T?)
    fun onError(error: String?)
}