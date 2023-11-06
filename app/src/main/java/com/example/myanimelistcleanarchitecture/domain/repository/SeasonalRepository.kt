package com.example.myanimelistcleanarchitecture.domain.repository

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.seasonal.SeasonalResponse
import kotlinx.coroutines.flow.Flow

interface SeasonalRepository {

    suspend fun getSeason(year: Int, season: String, filter: String): Flow<NetworkResult<SeasonalResponse>>
}