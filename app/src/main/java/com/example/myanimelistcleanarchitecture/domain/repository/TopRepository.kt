package com.example.myanimelistcleanarchitecture.domain.repository

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topanime.TopAnimeResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topmanga.TopMangaResponse
import kotlinx.coroutines.flow.Flow

interface TopRepository {

    suspend fun topAnime(): Flow<NetworkResult<TopAnimeResponse>>

    suspend fun topManga(): Flow<NetworkResult<TopMangaResponse>>
}