package com.platinacode.challenge.data.repositories

import android.content.Context
import com.deshipay.common.network.NetworkResult
import com.platinacode.challenge.data.dataSoruce.RemoteDataSource
import com.platinacode.challenge.data.models.noblePrizeResponse.ResponseNoblePrize
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource, @ApplicationContext context: Context):BaseRepository(context) {

    suspend fun getAllFilms(): Flow<NetworkResult<ResponseNoblePrize>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getAllFilms() })
        }.flowOn(Dispatchers.IO)
    }


}