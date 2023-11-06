package com.example.myanimelistcleanarchitecture.data

import com.example.myanimelistcleanarchitecture.domain.model.Response.reviews.AnimeReviewsResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.reviews.MangaReviewsResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.AnimeSearchResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.MangaSearchResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.seasonal.SeasonalResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.staff.AnimeStaffResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.staff.CharactersAndVoiceActorsResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.themes.AnimeThemeResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topanime.TopAnimeResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topmanga.TopMangaResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.watched.WatchedEpisodesResponse
import com.example.myanimelistcleanarchitecture.presentation.adapters.AnimeStaffAdapter
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("top/anime")
    suspend fun getTopAnime(): TopAnimeResponse

    @GET("top/manga")
    suspend fun getTopManga(): TopMangaResponse

    @GET("watch/episodes")
    suspend fun getRecentlyWatchedEpisode(): WatchedEpisodesResponse

    @GET("watch/episodes/popular")
    suspend fun getPopularWatchedEpisode(): WatchedEpisodesResponse

    @GET("watch/promos/popular")
    suspend fun getPopularPromoEpisodes(): WatchedEpisodesResponse

    @GET("seasons/{year}/{season}")
    suspend fun getSeason(
        @Path("year") year: Int,
        @Path("season") season: String,
        @Query("filter") filter: String
    ): SeasonalResponse

    @GET("anime/{id}/staff")
    suspend fun getStaff(
        @Path("id") id: Int
    ): AnimeStaffResponse

    @GET("anime/{id}/characters")
    suspend fun getCharactersAndVoiceActors(
        @Path("id") id: Int
    ): CharactersAndVoiceActorsResponse

    @GET("anime/{id}/themes")
    suspend fun getAnimeTheme(
        @Path("id") id: Int
    ): AnimeThemeResponse

    @GET("anime/{id}/reviews")
    suspend fun getAnimeReviews(
        @Path("id") id: Int
    ): AnimeReviewsResponse

    @GET("manga/{id}/reviews")
    suspend fun getMangaReviews(
        @Path("id") id: Int
    ): MangaReviewsResponse

    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String
    ): AnimeSearchResponse

    @GET("manga")
    suspend fun getMangaSearch(
        @Query("q") q: String
    ): MangaSearchResponse
}