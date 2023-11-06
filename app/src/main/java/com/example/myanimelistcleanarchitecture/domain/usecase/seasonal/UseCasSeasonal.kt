package com.example.myanimelistcleanarchitecture.domain.usecase.seasonal

import com.example.myanimelistcleanarchitecture.domain.repository.SeasonalRepository
import javax.inject.Inject

class UseCasSeasonal @Inject constructor(
    private val seasonalRepository: SeasonalRepository
) {
    suspend fun getSeason(year: Int, season: String, filter: String) =
        seasonalRepository.getSeason(year,season,filter)
}