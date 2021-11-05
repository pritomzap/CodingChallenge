package com.platinacode.challenge.data.repositories

import android.content.Context
import com.deshipay.common.network.NetworkResult
import com.platinacode.challenge.R
import retrofit2.Response


abstract class BaseRepository(private val context: Context){

    private var doOnUnauthenticate:(()->Unit)? = null

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        val response = apiCall()
        try {
            return if (response.isSuccessful && response.code() == 200) {
                val body = response.body()
                NetworkResult.Success(body,"")
            }else if (response.code() == 401){
                doOnUnauthenticate!!.invoke()
                error(response.code(),response.message()?:"Unauthenticated")
            }else
                error(response.code(), response.message())
        } catch (e: Exception) {
            return error(0, e.message?:context.getString(R.string.app_common_api_error))
        }
    }

    private fun <T> error(code:Int,errorMessage: String): NetworkResult<T> = NetworkResult.Error(code,errorMessage)

}