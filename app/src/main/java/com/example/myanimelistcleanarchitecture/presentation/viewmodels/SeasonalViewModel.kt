package com.example.myanimelistcleanarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.seasonal.SeasonalResponse
import com.example.myanimelistcleanarchitecture.domain.usecase.seasonal.UseCasSeasonal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonalViewModel @Inject constructor(
    private val useCaseSeasonal: UseCasSeasonal
) : ViewModel() {

    private var _seasonal = MutableLiveData<NetworkResult<SeasonalResponse>>()
    val seasonal: LiveData<NetworkResult<SeasonalResponse>> = _seasonal

    fun getSeasonal(year: Int, season: String, filter: String) {
        viewModelScope.launch {
            useCaseSeasonal.getSeason(year,season,filter).collect {
                _seasonal.postValue(it)
            }
        }
    }
}