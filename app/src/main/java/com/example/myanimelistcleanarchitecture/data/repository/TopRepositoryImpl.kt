package com.example.myanimelistcleanarchitecture.data.repository

import com.example.myanimelistcleanarchitecture.data.ApiService
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topanime.TopAnimeResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topmanga.TopMangaResponse
import com.example.myanimelistcleanarchitecture.domain.repository.TopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TopRepository {
    override suspend fun topAnime(): Flow<NetworkResult<TopAnimeResponse>> {

      val response =  flow {
            emit(NetworkResult.Loading(true))
            val response = apiService.getTopAnime()
            emit(NetworkResult.Success(response))
         //   emit(NetworkResult.Loading(false))

        }.catch { e ->
            emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
        }
        return response
    }

    override suspend fun topManga(): Flow<NetworkResult<TopMangaResponse>> {

        val response = flow {
            emit(NetworkResult.Loading(true))
            val response = apiService.getTopManga()
            emit(NetworkResult.Success(response))
         //   emit(NetworkResult.Loading(false))
        }.catch{e ->
            emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
        }

        return response
    }


}