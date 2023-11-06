package com.example.myanimelistcleanarchitecture.data.repository

import com.example.myanimelistcleanarchitecture.data.ApiService
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.watched.WatchedEpisodesResponse
import com.example.myanimelistcleanarchitecture.domain.repository.WatchedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WatchedRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WatchedRepository {

    override suspend fun recentlyWatchedEpisodes() = flow {

        emit(NetworkResult.Loading(true))
        val response = apiService.getRecentlyWatchedEpisode()
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    override suspend fun popularWatchedEpisodes() = flow {

        emit(NetworkResult.Loading(true))
        val response = apiService.getPopularWatchedEpisode()
        emit(NetworkResult.Success(response))
    }.catch { e->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    override suspend fun popularPromoEpisodes() = flow {

        emit(NetworkResult.Loading(true))
        val response = apiService.getPopularPromoEpisodes()
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}