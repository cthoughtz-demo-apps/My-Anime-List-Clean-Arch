package com.example.myanimelistcleanarchitecture.domain.usecase.search

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.AnimeSearchResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.MangaSearchResponse
import com.example.myanimelistcleanarchitecture.domain.repository.AnimeSearchRepository
import com.example.myanimelistcleanarchitecture.domain.repository.MangaSearchRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class UseCaseSearch @Inject constructor(
    private val searchAnimeSearchRepository: AnimeSearchRepository,
    private val searchMangaSearchRepository: MangaSearchRepository
) {

    suspend fun getSearchAnime(search: String) = searchAnimeSearchRepository.getAnimeSearch(search)
    suspend fun getSearchManga(search: String) = searchMangaSearchRepository.getMangaSearch(search)
}