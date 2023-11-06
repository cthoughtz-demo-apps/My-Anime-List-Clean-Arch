package com.example.myanimelistcleanarchitecture.domain.repository

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.themes.AnimeThemeResponse
import kotlinx.coroutines.flow.Flow

interface AnimeThemeRepository {

    suspend fun getAnimeTheme(id:Int): Flow<NetworkResult<AnimeThemeResponse>>
}