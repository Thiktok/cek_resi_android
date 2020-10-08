package com.ramadhan.couriertracking.data.network.response

import java.lang.Exception

sealed class DataResult<out T> {
    data class Success<out T>(val data: T?) : DataResult<T>()

    class Error(
        private val exception: Exception,
        val code: Int = 999,
        val errorMessage: String? = exception.localizedMessage
    ): DataResult<Nothing>()
}