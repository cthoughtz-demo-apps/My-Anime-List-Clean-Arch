package com.example.myanimelistcleanarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.watched.WatchedEpisodesResponse
import com.example.myanimelistcleanarchitecture.domain.usecase.watched.UseCaseWatched
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchedViewModel @Inject constructor(
    private val watchedUseCase: UseCaseWatched
) : ViewModel() {

    private var _recentlyWatchedEpisodes =
        MutableLiveData<NetworkResult<WatchedEpisodesResponse>>()
    val recentlyWatchedEpisodesResponse: LiveData<NetworkResult<WatchedEpisodesResponse>> = _recentlyWatchedEpisodes

    private var _popularEpisodes = MutableLiveData<NetworkResult<WatchedEpisodesResponse>>()
    val popularEpisodesResponse: LiveData<NetworkResult<WatchedEpisodesResponse>> = _popularEpisodes

    private var _recentPromos = MutableLiveData<NetworkResult<WatchedEpisodesResponse>>()
    val recentPromos: LiveData<NetworkResult<WatchedEpisodesResponse>> = _recentPromos

    fun recentlyWatchedEpisode() {
        viewModelScope.launch {
            watchedUseCase.getWatchedRecentEpisodes().collect {
                _recentlyWatchedEpisodes.postValue(it)
            }
        }
    }

    fun popularEpisodes() {
        viewModelScope.launch {
            watchedUseCase.getPopularEpisodes().collect {
                _popularEpisodes.postValue(it)
            }
        }
    }

    fun recentPromos() {
        viewModelScope.launch {
            watchedUseCase.getRecentPromos().collect {
                _recentPromos.postValue(it)
            }
        }
    }
}