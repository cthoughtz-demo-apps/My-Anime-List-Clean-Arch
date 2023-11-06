package com.example.myanimelistcleanarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.AnimeSearchResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.MangaSearchResponse
import com.example.myanimelistcleanarchitecture.domain.usecase.search.UseCaseSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCaseSearch: UseCaseSearch
) : ViewModel() {

    private val _animeSearch = MutableLiveData<NetworkResult<AnimeSearchResponse>>()
    val animeSearch: LiveData<NetworkResult<AnimeSearchResponse>> = _animeSearch

    private val _mangaSearch = MutableLiveData<NetworkResult<MangaSearchResponse>>()
    val mangaSearch: LiveData<NetworkResult<MangaSearchResponse>> = _mangaSearch

//    private val _allSearch = MutableLiveData<List<Pair<NetworkResult<AnimeSearchResponse>, NetworkResult<MangaSearchResponse>>>>()
//    val allSearch: LiveData<List<Pair<NetworkResult<AnimeSearchResponse>, NetworkResult<MangaSearchResponse>>>> = _allSearch

//    private val _allSearch = MutableLiveData<Pair<NetworkResult<AnimeSearchResponse>, NetworkResult<MangaSearchResponse>>>()
//    val allSearch: LiveData<Pair<NetworkResult<AnimeSearchResponse>, NetworkResult<MangaSearchResponse>>> = _allSearch

    private val _allSearch = MutableLiveData<Pair<MutableLiveData<NetworkResult<AnimeSearchResponse>>, MutableLiveData<NetworkResult<MangaSearchResponse>>>>()
    val allSearch: LiveData<Pair<MutableLiveData<NetworkResult<AnimeSearchResponse>>, MutableLiveData<NetworkResult<MangaSearchResponse>>>> = _allSearch



    fun searchAnime(search: String) {
        viewModelScope.launch {
            useCaseSearch.getSearchAnime(search).collect {
                _animeSearch.postValue(it)
            }
        }
    }

    fun searchManga(search: String) {
        viewModelScope.launch {
            useCaseSearch.getSearchManga(search).collect {
                _mangaSearch.postValue(it)
            }
        }
    }

    fun searchAll(search: String) {
        viewModelScope.launch {

            async {
                useCaseSearch.getSearchAnime(search).collect {
                    _animeSearch.postValue(it)
                }
            }.await()


            async {
                useCaseSearch.getSearchManga(search).collect {
                    _mangaSearch.postValue(it)
                }
            }.await()

            _allSearch.postValue(Pair(_animeSearch,_mangaSearch))
        }
    }

}