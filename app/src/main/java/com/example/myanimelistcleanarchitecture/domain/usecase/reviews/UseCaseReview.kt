package com.example.myanimelistcleanarchitecture.domain.usecase.reviews

import com.example.myanimelistcleanarchitecture.domain.repository.AnimeReviewRepository
import com.example.myanimelistcleanarchitecture.domain.repository.MangaReviewRepository
import javax.inject.Inject

class UseCaseReview @Inject constructor(
    private val animeReviewRepository: AnimeReviewRepository,
    private val mangaReviewRepository: MangaReviewRepository
) {
    suspend fun getReviewsAnime(id: Int) = animeReviewRepository.getAnimeReview(id)
    suspend fun getReviewsManga(id: Int) = mangaReviewRepository.getMangaReview(id)
}