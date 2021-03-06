package com.deshipay.common.network

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T?,successMessage:String? = "") : NetworkResult<T>(data,successMessage)

    class Error<T>(code:Int = 0,message: String, data: T? = null) : NetworkResult<T>(data, message)

    class Loading<T> : NetworkResult<T>()

}
