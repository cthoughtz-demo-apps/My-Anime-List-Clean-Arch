package com.example.myanimelistcleanarchitecture.domain.model.Response.themes


import com.google.gson.annotations.SerializedName

data class AnimeThemeResponse(
    @SerializedName("data")
    var `data`: Data
) {
    data class Data(
        @SerializedName("endings")
        var endings: List<String>,
        @SerializedName("openings")
        var openings: List<String>
    )
}