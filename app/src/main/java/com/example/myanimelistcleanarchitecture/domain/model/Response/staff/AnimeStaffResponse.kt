package com.example.myanimelistcleanarchitecture.domain.model.Response.staff


import com.google.gson.annotations.SerializedName

data class AnimeStaffResponse(
    @SerializedName("data")
    var `data`: List<Data>
) {
    data class Data(
        @SerializedName("person")
        var person: Person,
        @SerializedName("positions")
        var positions: List<String>
    ) {
        data class Person(
            @SerializedName("images")
            var images: Images,
            @SerializedName("mal_id")
            var malId: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("url")
            var url: String
        ) {
            data class Images(
                @SerializedName("jpg")
                var jpg: Jpg
            ) {
                data class Jpg(
                    @SerializedName("image_url")
                    var imageUrl: String
                )
            }
        }
    }
}