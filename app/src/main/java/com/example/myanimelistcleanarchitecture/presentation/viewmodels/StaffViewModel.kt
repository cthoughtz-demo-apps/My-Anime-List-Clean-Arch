package com.example.myanimelistcleanarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.staff.AnimeStaffResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.staff.CharactersAndVoiceActorsResponse
import com.example.myanimelistcleanarchitecture.domain.usecase.staff.UseCaseCharactersVoiceActors
import com.example.myanimelistcleanarchitecture.domain.usecase.staff.UseCaseStaff
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StaffViewModel @Inject constructor(
    private val useCaseStaff: UseCaseStaff,
    private val useCaseCharactersVoiceActors: UseCaseCharactersVoiceActors
) : ViewModel() {

    private var _charactersVoiceActors =
        MutableLiveData<NetworkResult<CharactersAndVoiceActorsResponse>>()
    val charactersVoiceActors: LiveData<NetworkResult<CharactersAndVoiceActorsResponse>> =_charactersVoiceActors

    private var _staff = MutableLiveData<NetworkResult<AnimeStaffResponse>>()
    val staff: LiveData<NetworkResult<AnimeStaffResponse>> = _staff

    fun getCharactersAndVoiceActors(id: Int) {
        viewModelScope.launch {
            useCaseCharactersVoiceActors.getCharactersAndVoiceActors(id).collect {
                _charactersVoiceActors.postValue(it)
            }
        }
    }

    fun getStaff(id: Int) {
        viewModelScope.launch {
            useCaseStaff.getStaff(id).collect {
                _staff.postValue(it)
            }
        }
    }
}