package com.example.myanimelistcleanarchitecture.domain.repository

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.MangaSearchResponse
import kotlinx.coroutines.flow.Flow

interface MangaSearchRepository {

    suspend fun getMangaSearch(search: String): Flow<NetworkResult<MangaSearchResponse>>
}