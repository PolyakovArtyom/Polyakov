package com.example.polyakov.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class FilmsServerModel(
    val pagesCount: Int,
    val films: List<Films>
)

@Entity(tableName = "films_table")
data class Films(
    @SerializedName("filmId")
    @PrimaryKey
    val filmId: Int,

    @SerializedName("nameRu")
    val name: String,
//    val nameEn: String? = null,
    val year: String,
//    val filmLength: String,
    val countries: List<Country>,
    val genres: List<Genre>,
//    val rating: String,
//    val ratingVoteCount: Int,

    @SerializedName("posterUrl")
    @ColumnInfo(name = "poster_URL")
    val posterURL: String,

    @SerializedName("posterUrlPreview")
    @ColumnInfo(name = "poster_URL_preview")
    val posterURLPreview: String,

//    val description: String

//    val ratingChange: Any? = null
) {
//    fun to(): String {
//        return filmID.toString() + name + year
//    }
}

data class Country(
    val country: String
)

data class Genre(
    val genre: String
)
