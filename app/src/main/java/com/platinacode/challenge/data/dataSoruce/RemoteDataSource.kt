package com.platinacode.challenge.data.dataSoruce

import com.deshipay.common.network.ApiServices
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiServices: ApiServices) {

    suspend fun getAllFilms() = apiServices.getAllFilms()

}