package com.example.myanimelistcleanarchitecture.data.repository

import com.example.myanimelistcleanarchitecture.data.ApiService
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.repository.AnimeSearchRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeSearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AnimeSearchRepository {
    override suspend fun getAnimeSearch(search: String) = flow {

        emit(NetworkResult.Loading(true))
        val response = apiService.getAnimeSearch(search)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit((NetworkResult.Failure(e.message ?: "Unknown Error")))
    }
}