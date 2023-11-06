package com.example.myanimelistcleanarchitecture.data.repository

import com.example.myanimelistcleanarchitecture.data.ApiService
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.repository.MangaSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MangaSearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MangaSearchRepository {
    override suspend fun getMangaSearch(search: String) = flow {

        emit(NetworkResult.Loading(true))
        val response = apiService.getMangaSearch(search)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit((NetworkResult.Failure(e.message ?: "Unknown Error")))
    }
}