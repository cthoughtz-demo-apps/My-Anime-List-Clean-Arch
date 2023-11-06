package com.example.myanimelistcleanarchitecture.data.repository

import com.example.myanimelistcleanarchitecture.data.ApiService
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.repository.MangaReviewRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MangaReviewRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MangaReviewRepository {
    override suspend fun getMangaReview(id: Int) = flow {

        emit(NetworkResult.Loading(true))
        val response = apiService.getMangaReviews(id)
        emit(NetworkResult.Success((response)))
    }.catch { e->
        emit((NetworkResult.Failure(e.message ?: "Unknown Error")))
    }
}