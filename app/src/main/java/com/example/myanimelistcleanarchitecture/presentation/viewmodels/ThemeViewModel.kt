package com.example.myanimelistcleanarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.themes.AnimeThemeResponse
import com.example.myanimelistcleanarchitecture.domain.usecase.themes.UseCaseThemes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val useCaseThemes: UseCaseThemes
): ViewModel() {

    private var _themes =
        MutableLiveData<NetworkResult<AnimeThemeResponse>>()
    val theme: LiveData<NetworkResult<AnimeThemeResponse>> = _themes

    fun getThemes(id: Int) {
        viewModelScope.launch {
            useCaseThemes.getAnimeThemes(id).collect{
                _themes.postValue(it)
            }
        }
    }
}