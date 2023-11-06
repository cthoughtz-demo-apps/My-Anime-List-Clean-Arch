package com.example.myanimelistcleanarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.reviews.AnimeReviewsResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.reviews.MangaReviewsResponse
import com.example.myanimelistcleanarchitecture.domain.usecase.reviews.UseCaseReview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val useCaseReview: UseCaseReview
) : ViewModel() {

    private var _animeReviews = MutableLiveData<NetworkResult<AnimeReviewsResponse>>()
    val animeReviews: LiveData<NetworkResult<AnimeReviewsResponse>> = _animeReviews

    private var _mangaReviews = MutableLiveData<NetworkResult<MangaReviewsResponse>>()
    val mangaReviews: LiveData<NetworkResult<MangaReviewsResponse>> = _mangaReviews

    fun getReviewsAnime(id:Int) {
        viewModelScope.launch {
            useCaseReview.getReviewsAnime(id).collect {
                _animeReviews.postValue(it)
            }
        }
    }

    fun getReviewsManga(id: Int) {
        viewModelScope.launch {
            useCaseReview.getReviewsManga(id).collect {
                _mangaReviews.postValue(it)
            }
        }
    }
}