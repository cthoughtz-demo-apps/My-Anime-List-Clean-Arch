package com.example.myanimelistcleanarchitecture.domain.usecase.themes

import com.example.myanimelistcleanarchitecture.domain.repository.AnimeThemeRepository
import javax.inject.Inject

class UseCaseThemes @Inject constructor(
    private val animeThemeRepository: AnimeThemeRepository
) {
    suspend fun getAnimeThemes(id: Int) = animeThemeRepository.getAnimeTheme(id)
}