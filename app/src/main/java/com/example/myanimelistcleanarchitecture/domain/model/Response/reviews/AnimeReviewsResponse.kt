package com.example.myanimelistcleanarchitecture.domain.model.Response.reviews


import com.google.gson.annotations.SerializedName

data class AnimeReviewsResponse(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("pagination")
    var pagination: Pagination
) {
    data class Data(
        @SerializedName("date")
        var date: String,
        @SerializedName("episodes_watched")
        var episodesWatched: Int,
        @SerializedName("is_preliminary")
        var isPreliminary: Boolean,
        @SerializedName("is_spoiler")
        var isSpoiler: Boolean,
        @SerializedName("mal_id")
        var malId: Int,
        @SerializedName("reactions")
        var reactions: Reactions,
        @SerializedName("review")
        var review: String,
        @SerializedName("score")
        var score: Int,
        @SerializedName("tags")
        var tags: List<String>,
        @SerializedName("type")
        var type: String,
        @SerializedName("url")
        var url: String,
        @SerializedName("user")
        var user: User
    ) {
        data class Reactions(
            @SerializedName("confusing")
            var confusing: Int,
            @SerializedName("creative")
            var creative: Int,
            @SerializedName("funny")
            var funny: Int,
            @SerializedName("informative")
            var informative: Int,
            @SerializedName("love_it")
            var loveIt: Int,
            @SerializedName("nice")
            var nice: Int,
            @SerializedName("overall")
            var overall: Int,
            @SerializedName("well_written")
            var wellWritten: Int
        )

        data class User(
            @SerializedName("images")
            var images: Images,
            @SerializedName("url")
            var url: String,
            @SerializedName("username")
            var username: String
        ) {
            data class Images(
                @SerializedName("jpg")
                var jpg: Jpg,
                @SerializedName("webp")
                var webp: Webp
            ) {
                data class Jpg(
                    @SerializedName("image_url")
                    var imageUrl: String
                )

                data class Webp(
                    @SerializedName("image_url")
                    var imageUrl: String
                )
            }
        }
    }

    data class Pagination(
        @SerializedName("has_next_page")
        var hasNextPage: Boolean,
        @SerializedName("last_visible_page")
        var lastVisiblePage: Int
    )
}