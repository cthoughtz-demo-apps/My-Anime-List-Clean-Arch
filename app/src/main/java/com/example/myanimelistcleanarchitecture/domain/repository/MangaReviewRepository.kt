package com.example.myanimelistcleanarchitecture.domain.repository

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.reviews.MangaReviewsResponse
import kotlinx.coroutines.flow.Flow

interface MangaReviewRepository {

    suspend fun getMangaReview(id: Int): Flow<NetworkResult<MangaReviewsResponse>>
}