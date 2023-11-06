package com.example.myanimelistcleanarchitecture.data.di

import com.example.myanimelistcleanarchitecture.data.repository.AnimeReviewRepositoryImpl
import com.example.myanimelistcleanarchitecture.data.repository.AnimeSearchRepositoryImpl
import com.example.myanimelistcleanarchitecture.data.repository.AnimeThemeImpl
import com.example.myanimelistcleanarchitecture.data.repository.CharacterAndVoiceActorRepositoryImplRepository
import com.example.myanimelistcleanarchitecture.data.repository.MangaReviewRepositoryImpl
import com.example.myanimelistcleanarchitecture.data.repository.MangaSearchRepositoryImpl
import com.example.myanimelistcleanarchitecture.data.repository.SeasonalRepositoryImpl
import com.example.myanimelistcleanarchitecture.data.repository.StaffRepositoryImpl
import com.example.myanimelistcleanarchitecture.data.repository.TopRepositoryImpl
import com.example.myanimelistcleanarchitecture.data.repository.WatchedRepositoryImpl
import com.example.myanimelistcleanarchitecture.domain.repository.AnimeReviewRepository
import com.example.myanimelistcleanarchitecture.domain.repository.AnimeSearchRepository
import com.example.myanimelistcleanarchitecture.domain.repository.AnimeThemeRepository
import com.example.myanimelistcleanarchitecture.domain.repository.CharactersAndVoiceActorsRepository
import com.example.myanimelistcleanarchitecture.domain.repository.MangaReviewRepository
import com.example.myanimelistcleanarchitecture.domain.repository.MangaSearchRepository
import com.example.myanimelistcleanarchitecture.domain.repository.SeasonalRepository
import com.example.myanimelistcleanarchitecture.domain.repository.StaffRepository
import com.example.myanimelistcleanarchitecture.domain.repository.TopRepository
import com.example.myanimelistcleanarchitecture.domain.repository.WatchedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModules {

    @Singleton
    @Provides
    fun provideTopRepositoryImpl(topRepositoryImpl: TopRepositoryImpl): TopRepository {
        return topRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideWatchRepositoryImpl(watchedRepositoryImpl: WatchedRepositoryImpl): WatchedRepository {
        return watchedRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideSeasonalImpl(seasonalRepositoryImpl: SeasonalRepositoryImpl) : SeasonalRepository {
        return seasonalRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideStaffImpl(staffRepositoryImpl: StaffRepositoryImpl): StaffRepository {
        return staffRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideCharactersAndVoiceActorsImpl(characterAndVoiceActorRepositoryImpl: CharacterAndVoiceActorRepositoryImplRepository): CharactersAndVoiceActorsRepository {
        return characterAndVoiceActorRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideAnimeThemes(animeThemeImpl: AnimeThemeImpl): AnimeThemeRepository {
        return animeThemeImpl
    }

    @Singleton
    @Provides
    fun provideAnimeReviews(animeReviewRepositoryImpl: AnimeReviewRepositoryImpl): AnimeReviewRepository {
        return animeReviewRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideMangaReviews(mangaReviewRepositoryImpl: MangaReviewRepositoryImpl): MangaReviewRepository {
        return mangaReviewRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideAnimeSearch(animeSearchRepositoryImpl: AnimeSearchRepositoryImpl): AnimeSearchRepository {
        return animeSearchRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideMangaSearch(mangaSearchRepositoryImpl: MangaSearchRepositoryImpl): MangaSearchRepository {
        return mangaSearchRepositoryImpl
    }
}