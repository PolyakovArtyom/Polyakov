package com.example.polyakov.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.polyakov.domain.CommonFilmsItem
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

    val year: String,
    val countries: List<Country>,
    val genres: List<Genre>,

    @SerializedName("posterUrl")
    @ColumnInfo(name = "poster_URL")
    val posterURL: String,

    @SerializedName("posterUrlPreview")
    @ColumnInfo(name = "poster_URL_preview")
    val posterURLPreview: String,

    ) {
    fun toItem() =
        CommonFilmsItem(filmId, posterURL, filmName = name, genres = genres[0].genre, year = year)
}

data class Country(
    val country: String
)

data class Genre(
    val genre: String
)
