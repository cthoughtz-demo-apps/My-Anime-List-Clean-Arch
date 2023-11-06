package com.example.myanimelistcleanarchitecture.domain.usecase.watched

import com.example.myanimelistcleanarchitecture.domain.repository.WatchedRepository
import javax.inject.Inject

class UseCaseWatched @Inject constructor(
    private val watchedRepository: WatchedRepository
){

    suspend fun getWatchedRecentEpisodes() = watchedRepository.recentlyWatchedEpisodes()
    suspend fun getPopularEpisodes() = watchedRepository.popularWatchedEpisodes()
    suspend fun getRecentPromos() = watchedRepository.popularPromoEpisodes()
}