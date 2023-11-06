package com.example.myanimelistcleanarchitecture.domain.repository

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.reviews.AnimeReviewsResponse
import kotlinx.coroutines.flow.Flow

interface AnimeReviewRepository {

    suspend fun getAnimeReview(id: Int): Flow<NetworkResult<AnimeReviewsResponse>>
}