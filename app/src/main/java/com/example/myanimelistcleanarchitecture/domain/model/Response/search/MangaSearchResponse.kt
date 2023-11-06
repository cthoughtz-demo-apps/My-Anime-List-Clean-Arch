package com.example.myanimelistcleanarchitecture.domain.model.Response.search


import com.google.gson.annotations.SerializedName

data class MangaSearchResponse(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("pagination")
    var pagination: Pagination
) {
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
    ) {
        data class Author(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        )

        data class Demographic(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        )

        data class ExplicitGenre(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        )

        data class Genre(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        )

        data class Images(
            @SerializedName("jpg")
            var jpg: Jpg,
            @SerializedName("webp")
            var webp: Webp
        ) {
            data class Jpg(
                @SerializedName("image_url")
                var imageUrl: String,
                @SerializedName("large_image_url")
                var largeImageUrl: String,
                @SerializedName("small_image_url")
                var smallImageUrl: String
            )

            data class Webp(
                @SerializedName("image_url")
                var imageUrl: String,
                @SerializedName("large_image_url")
                var largeImageUrl: String,
                @SerializedName("small_image_url")
                var smallImageUrl: String
            )
        }

        data class Published(
            @SerializedName("from")
            var from: String,
            @SerializedName("prop")
            var prop: Prop,
            @SerializedName("to")
            var to: String
        ) {
            data class Prop(
                @SerializedName("from")
                var from: From,
                @SerializedName("string")
                var string: String,
                @SerializedName("to")
                var to: To
            ) {
                data class From(
                    @SerializedName("day")
                    var day: Int,
                    @SerializedName("month")
                    var month: Int,
                    @SerializedName("year")
                    var year: Int
                )

                data class To(
                    @SerializedName("day")
                    var day: Int,
                    @SerializedName("month")
                    var month: Int,
                    @SerializedName("year")
                    var year: Int
                )
            }
        }

        data class Serialization(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        )

        data class Theme(
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        )

        data class Title(
            @SerializedName("title")
            var title: String,
            @SerializedName("type")
            var type: String
        )
    }

    data class Pagination(
        @SerializedName("has_next_page")
        var hasNextPage: Boolean,
        @SerializedName("items")
        var items: Items,
        @SerializedName("last_visible_page")
        var lastVisiblePage: Int
    ) {
        data class Items(
            @SerializedName("count")
            var count: Int,
            @SerializedName("per_page")
            var perPage: Int,
            @SerializedName("total")
            var total: Int
        )
    }
}