package com.example.myanimelistcleanarchitecture.domain.model.Response.staff


import com.google.gson.annotations.SerializedName

data class CharactersAndVoiceActorsResponse(
    @SerializedName("data")
    var `data`: List<Data>
) {
    data class Data(
        @SerializedName("character")
        var character: Character,
        @SerializedName("role")
        var role: String,
        @SerializedName("voice_actors")
        var voiceActors: List<VoiceActor>
    ) {
        data class Character(
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
                var jpg: Jpg,
                @SerializedName("webp")
                var webp: Webp
            ) {
                data class Jpg(
                    @SerializedName("image_url")
                    var imageUrl: String,
                    @SerializedName("small_image_url")
                    var smallImageUrl: String
                )

                data class Webp(
                    @SerializedName("image_url")
                    var imageUrl: String,
                    @SerializedName("small_image_url")
                    var smallImageUrl: String
                )
            }
        }

        data class VoiceActor(
            @SerializedName("language")
            var language: String,
            @SerializedName("person")
            var person: Person
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
}