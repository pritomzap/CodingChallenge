package com.platinacode.challenge.view.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.deshipay.common.network.NetworkResult
import com.platinacode.challenge.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val mApplication: Application): AndroidViewModel(mApplication) {

    companion object{
        private const val EXCEPTION_MESSAGE_REGEX = "Unable to resolve"
    }

    suspend fun <T>flowHandler(responseLiveData: MutableLiveData<NetworkResult<T>>, flowValue:suspend ()-> Flow<NetworkResult<T>>) = viewModelScope.launch{
        flowValue.invoke()
            .onStart {
                responseLiveData.value = NetworkResult.Loading()
            }
            .catch { exception->
                if (exception.message?.contains(EXCEPTION_MESSAGE_REGEX) == true)
                    responseLiveData.value = NetworkResult.Error(message = mApplication.getString(R.string.app_common_internet_not_connected))
                else
                    responseLiveData.value = NetworkResult.Error(message = exception.localizedMessage?:exception.message?:mApplication.getString(R.string.app_common_internet_not_connected))
            }
            .collect { data->
                responseLiveData.value = data
            }
    }

    fun <T>flowHandlerLiveData(otherLiveData: MutableLiveData<NetworkResult<T>>? = null, flowValue:suspend ()-> Flow<NetworkResult<T>>): LiveData<NetworkResult<T>> = liveData{
        flowValue.invoke()
            .onStart { emit(NetworkResult.Loading()) }
            .catch { exception ->
                if (exception.message?.contains(EXCEPTION_MESSAGE_REGEX) == true)
                    emit(NetworkResult.Error(message = mApplication.getString(R.string.app_common_internet_not_connected)))
                else
                    emit(NetworkResult.Error(message = exception.localizedMessage ?: exception.message ?: mApplication.getString(R.string.app_common_internet_not_connected)))
            }.collect {
                if (otherLiveData!= null)
                    otherLiveData.value = it
                emit(it)
            }
    }

}