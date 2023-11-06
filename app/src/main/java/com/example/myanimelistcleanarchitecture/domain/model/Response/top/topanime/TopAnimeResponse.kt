package com.example.myanimelistcleanarchitecture.domain.model.Response.top.topanime


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopAnimeResponse(
    @SerializedName("data")
    var topAnimeData: List<Data>,
    @SerializedName("pagination")
    var pagination: Pagination
): Parcelable {

    @Parcelize
    data class Data(
        @SerializedName("aired")
        var aired: Aired,
        @SerializedName("airing")
        var airing: Boolean,
        @SerializedName("approved")
        var approved: Boolean,
        @SerializedName("background")
        var background: String,
        @SerializedName("broadcast")
        var broadcast: Broadcast,
        @SerializedName("demographics")
        var demographics: List<Demographic>,
        @SerializedName("duration")
        var duration: String,
        @SerializedName("episodes")
        var episodes: Int,
        @SerializedName("explicit_genres")
        var explicitGenres: List<ExplicitGenre>,
        @SerializedName("favorites")
        var favorites: Int,
        @SerializedName("genres")
        var genres: List<Genre>,
        @SerializedName("images")
        var images: Images,
        @SerializedName("licensors")
        var licensors: List<Licensor>,
        @SerializedName("mal_id")
        var malId: Int,
        @SerializedName("members")
        var members: Int,
        @SerializedName("popularity")
        var popularity: Int,
        @SerializedName("producers")
        var producers: List<Producer>,
        @SerializedName("rank")
        var rank: Int,
        @SerializedName("rating")
        var rating: String,
        @SerializedName("score")
        var score: Double,
        @SerializedName("scored_by")
        var scoredBy: Int,
        @SerializedName("season")
        var season: String,
        @SerializedName("source")
        var source: String,
        @SerializedName("status")
        var status: String,
        @SerializedName("studios")
        var studios: List<Studio>,
        @SerializedName("synopsis")
        var synopsis: String,
        @SerializedName("themes")
        var themes: List<Theme>,
        @SerializedName("title")
        var title: String,
        @SerializedName("title_english")
        var titleEnglish: String,
        @SerializedName("title_japanese")
        var titleJapanese: String,
        @SerializedName("title_synonyms")
        var titleSynonyms: List<String>,
        @SerializedName("titles")
        var titles: List<Title>,
        @SerializedName("trailer")
        var trailer: Trailer,
        @SerializedName("type")
        var type: String,
        @SerializedName("url")
        var url: String,
        @SerializedName("year")
        var year: Int
    ) : Parcelable {
        @Parcelize
        data class Aired(
            @SerializedName("from")
            var from: String,
            @SerializedName("prop")
            var prop: Prop,
            @SerializedName("to")
            var to: String
        ): Parcelable {
            @Parcelize
            data class Prop(
                @SerializedName("from")
                var from: From,
                @SerializedName("string")
                var string: String,
                @SerializedName("to")
                var to: To
            ) : Parcelable {
                @Parcelize
                data class From(
                    @SerializedName("day")
                    var day: Int,
                    @SerializedName("month")
                    var month: Int,
                    @SerializedName("year")
                    var year: Int
                ): Parcelable

                @Parcelize
                data class To(
                    @SerializedName("day")
                    var day: Int,
                    @SerializedName("month")
                    var month: Int,
                    @SerializedName("year")
                    var year: Int
                ): Parcelable
            }
        }

        @Parcelize
        data class Broadcast(
            @SerializedName("day")
            var day: String,
            @SerializedName("string")
            var string: String,
            @SerializedName("time")
            var time: String,
            @SerializedName("timezone")
            var timezone: String
        ): Parcelable

        @Parcelize
        data class Demographic(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        ): Parcelable

        @Parcelize
        data class ExplicitGenre(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        ): Parcelable

        @Parcelize
        data class Genre(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        ): Parcelable

        @Parcelize
        data class Images(
            @SerializedName("jpg")
            var jpg: Jpg,
            @SerializedName("webp")
            var webp: Webp
        ): Parcelable {
            @Parcelize
            data class Jpg(
                @SerializedName("image_url")
                var imageUrl: String,
                @SerializedName("large_image_url")
                var largeImageUrl: String,
                @SerializedName("small_image_url")
                var smallImageUrl: String
            ): Parcelable

            @Parcelize
            data class Webp(
                @SerializedName("image_url")
                var imageUrl: String,
                @SerializedName("large_image_url")
                var largeImageUrl: String,
                @SerializedName("small_image_url")
                var smallImageUrl: String
            ): Parcelable
        }

        @Parcelize
        data class Licensor(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        ): Parcelable

        @Parcelize
        data class Producer(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        ): Parcelable

        @Parcelize
        data class Studio(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        ): Parcelable

        @Parcelize
        data class Theme(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        ): Parcelable

        @Parcelize
        data class Title(
            @SerializedName("title")
            var title: String,
            @SerializedName("type")
            var type: String
        ): Parcelable

        @Parcelize
        data class Trailer(
            @SerializedName("embed_url")
            var embedUrl: String,
            @SerializedName("url")
            var url: String,
            @SerializedName("youtube_id")
            var youtubeId: String
        ): Parcelable
    }

    @Parcelize
    data class Pagination(
        @SerializedName("has_next_page")
        var hasNextPage: Boolean,
        @SerializedName("items")
        var items: Items,
        @SerializedName("last_visible_page")
        var lastVisiblePage: Int
    ): Parcelable {

        @Parcelize
        data class Items(
            @SerializedName("count")
            var count: Int,
            @SerializedName("per_page")
            var perPage: Int,
            @SerializedName("total")
            var total: Int
        ): Parcelable
    }
}