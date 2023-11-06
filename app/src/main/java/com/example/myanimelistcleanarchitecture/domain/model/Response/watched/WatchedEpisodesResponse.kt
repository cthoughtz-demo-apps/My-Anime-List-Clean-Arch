package com.example.myanimelistcleanarchitecture.domain.model.Response.watched


import com.google.gson.annotations.SerializedName

data class WatchedEpisodesResponse(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("pagination")
    var pagination: Pagination
) {
    data class Data(
        @SerializedName("entry")
        var entry: Entry,
        @SerializedName("episodes")
        var episodes: List<Episode>,
        @SerializedName("region_locked")
        var regionLocked: Boolean
    ) {
        data class Entry(
            @SerializedName("images")
            var images: Images,
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("title")
            var title: String,
            @SerializedName("url")
            var url: String
        ) {
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
        }

        data class Episode(
            @SerializedName("mal_id")
            var malId: String,
            @SerializedName("premium")
            var premium: Boolean,
            @SerializedName("title")
            var title: String,
            @SerializedName("url")
            var url: String
        )
    }

    data class Pagination(
        @SerializedName("has_next_page")
        var hasNextPage: Boolean,
        @SerializedName("last_visible_page")
        var lastVisiblePage: Int
    )
}