package com.example.myanimelistcleanarchitecture.domain.usecase.top

import com.example.myanimelistcleanarchitecture.domain.repository.TopRepository
import javax.inject.Inject

class UseCaseTopAnime @Inject constructor(
    private val topRepository: TopRepository
) {

    suspend fun getTopAnime() = topRepository.topAnime()
}