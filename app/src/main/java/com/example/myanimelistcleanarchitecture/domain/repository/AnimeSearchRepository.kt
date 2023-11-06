package com.example.myanimelistcleanarchitecture.domain.repository

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.AnimeSearchResponse
import kotlinx.coroutines.flow.Flow

interface AnimeSearchRepository {

    suspend fun getAnimeSearch(search: String) : Flow<NetworkResult<AnimeSearchResponse>>
}