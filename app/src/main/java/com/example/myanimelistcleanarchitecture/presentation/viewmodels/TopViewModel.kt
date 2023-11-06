package com.example.myanimelistcleanarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topanime.TopAnimeResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topmanga.TopMangaResponse
import com.example.myanimelistcleanarchitecture.domain.usecase.top.UseCaseTopAnime
import com.example.myanimelistcleanarchitecture.domain.usecase.top.UseCaseTopManga
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    private val topUseCaseTopAnime: UseCaseTopAnime,
    private val topUseCaseTopManga: UseCaseTopManga
) : ViewModel() {

    private var _topAnimeResponse = MutableLiveData<NetworkResult<TopAnimeResponse>>()
    val topAnimeResponse: LiveData<NetworkResult<TopAnimeResponse>> = _topAnimeResponse

    private var _topMangaResponse = MutableLiveData<NetworkResult<TopMangaResponse>>()
    val topMangaResponse: LiveData<NetworkResult<TopMangaResponse>> = _topMangaResponse

    fun topAnime() {
        viewModelScope.launch {
            topUseCaseTopAnime.getTopAnime().collect {
                _topAnimeResponse.postValue(it)
            }
        }
    }

    fun topManga() {
        viewModelScope.launch {
            topUseCaseTopManga.getTopManga().collect {
                _topMangaResponse.postValue(it)
            }
        }
    }
}