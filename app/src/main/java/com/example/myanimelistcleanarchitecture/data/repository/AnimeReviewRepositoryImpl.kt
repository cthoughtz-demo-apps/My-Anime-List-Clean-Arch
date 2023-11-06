package com.example.myanimelistcleanarchitecture.data.repository

import com.example.myanimelistcleanarchitecture.data.ApiService
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.reviews.AnimeReviewsResponse
import com.example.myanimelistcleanarchitecture.domain.repository.AnimeReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeReviewRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AnimeReviewRepository {
    override suspend fun getAnimeReview(id: Int) = flow {

        emit(NetworkResult.Loading(true))
        val response = apiService.getAnimeReviews(id)
        emit(NetworkResult.Success(response))
    }.catch { e->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}