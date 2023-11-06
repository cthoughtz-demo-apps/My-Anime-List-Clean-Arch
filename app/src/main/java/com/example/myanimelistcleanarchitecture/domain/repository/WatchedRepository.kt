package com.example.myanimelistcleanarchitecture.domain.repository

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.watched.WatchedEpisodesResponse
import kotlinx.coroutines.flow.Flow

interface WatchedRepository {

    suspend fun recentlyWatchedEpisodes(): Flow<NetworkResult<WatchedEpisodesResponse>>
    suspend fun popularWatchedEpisodes(): Flow<NetworkResult<WatchedEpisodesResponse>>
    suspend fun popularPromoEpisodes(): Flow<NetworkResult<WatchedEpisodesResponse>>
}