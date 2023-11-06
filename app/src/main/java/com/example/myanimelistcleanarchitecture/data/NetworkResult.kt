package com.example.myanimelistcleanarchitecture.data

import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topanime.TopAnimeResponse

sealed class NetworkResult<T> {
    data class Loading<T>(val isLoading: Boolean) : NetworkResult<T>()
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Failure<T>(val errorMessage: String): NetworkResult<T>()
}
