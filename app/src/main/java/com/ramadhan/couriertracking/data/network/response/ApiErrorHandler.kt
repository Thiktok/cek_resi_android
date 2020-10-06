package com.ramadhan.couriertracking.data.network.response

import com.google.gson.GsonBuilder
import retrofit2.Response
import java.io.IOException

class ApiErrorHandler {
    fun parseError(response: Response<*>): ApiError{
        val gson = GsonBuilder().create()
        val error: ApiError

        try {
            error = gson.fromJson(response.errorBody()?.string(), ApiError::class.java)
        } catch (e: IOException){
            return ApiError()
        }
        return error
    }
}

data class ApiError(
    val status: Int,
    val message: String
){
    constructor(): this(0, "")
}