package com.example.polyakov.data

import com.google.gson.annotations.SerializedName

data class FilmServerModel(
    val pagesCount: Int,
    val films: List<Film>
)

data class Film(
    @SerializedName("filmId")
    val filmID: Int,

    val nameRu: String,
    val nameEn: String? = null,
    val year: String,
    val filmLength: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val rating: String,
    val ratingVoteCount: Int,

    @SerializedName("posterUrl")
    val posterURL: String,

    @SerializedName("posterUrlPreview")
    val posterURLPreview: String,

    val ratingChange: Any? = null
) {
    fun to(): String {
        return filmID.toString() + nameRu + year
    }
}

data class Country(
    val country: String
)

data class Genre(
    val genre: String
)
