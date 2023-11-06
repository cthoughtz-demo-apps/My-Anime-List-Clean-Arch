package com.example.myanimelistcleanarchitecture.domain.model.Response.top.topmanga


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopMangaResponse(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("pagination")
    var pagination: Pagination
): Parcelable {

    @Parcelize
    data class Data(
        @SerializedName("approved")
        var approved: Boolean,
        @SerializedName("authors")
        var authors: List<Author>,
        @SerializedName("background")
        var background: String,
        @SerializedName("chapters")
        var chapters: Int,
        @SerializedName("demographics")
        var demographics: List<Demographic>,
        @SerializedName("explicit_genres")
        var explicitGenres: List<ExplicitGenre>,
        @SerializedName("favorites")
        var favorites: Int,
        @SerializedName("genres")
        var genres: List<Genre>,
        @SerializedName("images")
        var images: Images,
        @SerializedName("mal_id")
        var malId: Int,
        @SerializedName("members")
        var members: Int,
        @SerializedName("popularity")
        var popularity: Int,
        @SerializedName("published")
        var published: Published,
        @SerializedName("publishing")
        var publishing: Boolean,
        @SerializedName("rank")
        var rank: Int,
        @SerializedName("score")
        var score: Double,
        @SerializedName("scored_by")
        var scoredBy: Int,
        @SerializedName("serializations")
        var serializations: List<Serialization>,
        @SerializedName("status")
        var status: String,
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
        @SerializedName("titles")
        var titles: List<Title>,
        @SerializedName("type")
        var type: String,
        @SerializedName("url")
        var url: String,
        @SerializedName("volumes")
        var volumes: Int
    ) : Parcelable {

        @Parcelize
        data class Author(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        ) : Parcelable

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
        ) : Parcelable {

            @Parcelize
            data class Jpg(
                @SerializedName("image_url")
                var imageUrl: String,
                @SerializedName("large_image_url")
                var largeImageUrl: String,
                @SerializedName("small_image_url")
                var smallImageUrl: String
            ) : Parcelable

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
        data class Published(
            @SerializedName("from")
            var from: String,
            @SerializedName("prop")
            var prop: Prop,
            @SerializedName("to")
            var to: String
        ) : Parcelable {

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
        data class Serialization(
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
    }

    @Parcelize
    data class Pagination(
        @SerializedName("has_next_page")
        var hasNextPage: Boolean,
        @SerializedName("items")
        var items: Items,
        @SerializedName("last_visible_page")
        var lastVisiblePage: Int
    ) : Parcelable {

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