package com.example.myanimelistcleanarchitecture.data.repository

import com.example.myanimelistcleanarchitecture.data.ApiService
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.repository.SeasonalRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SeasonalRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SeasonalRepository{
    override suspend fun getSeason(
        year: Int,
        season: String,
        filter: String
    ) = flow {

        emit(NetworkResult.Loading(true))
        val response = apiService.getSeason(year,season,filter)
        emit(NetworkResult.Success(response))
    }.catch { e->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}