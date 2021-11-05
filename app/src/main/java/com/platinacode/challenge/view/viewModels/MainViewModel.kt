package com.platinacode.challenge.view.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deshipay.common.network.NetworkResult
import com.platinacode.challenge.data.models.noblePrizeResponse.LaureatesItem
import com.platinacode.challenge.data.models.noblePrizeResponse.ResponseNoblePrize
import com.platinacode.challenge.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository,application: Application):BaseViewModel(application){

    private val _filmsResponse: MutableLiveData<NetworkResult<ResponseNoblePrize>> = MutableLiveData()
    val filmsResponse: LiveData<NetworkResult<ResponseNoblePrize>> = _filmsResponse

    fun getFilms() = flowHandlerLiveData(_filmsResponse){repository.getAllFilms()}

    private val _selectedNobileHolder:MutableLiveData<LaureatesItem> = MutableLiveData()
    fun getSelectedItem() = _selectedNobileHolder
    fun setSelectedItem(item:LaureatesItem?) = viewModelScope.launch { _selectedNobileHolder.value = item }
}