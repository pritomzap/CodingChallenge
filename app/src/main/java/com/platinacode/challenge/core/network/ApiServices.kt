package com.deshipay.common.network

import com.platinacode.challenge.data.models.noblePrizeResponse.ResponseNoblePrize
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("2.1/laureates")
    suspend fun getAllFilms(): Response<ResponseNoblePrize>
}