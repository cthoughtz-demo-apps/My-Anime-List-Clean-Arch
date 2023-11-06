package com.example.myanimelistcleanarchitecture.data.repository

import com.example.myanimelistcleanarchitecture.data.ApiService
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.repository.AnimeThemeRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeThemeImpl @Inject constructor(
    private val apiService: ApiService
) : AnimeThemeRepository {
    override suspend fun getAnimeTheme(id: Int) = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getAnimeTheme(id)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}