package com.example.polyakov.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.polyakov.domain.CommonFilmsItem
import com.google.gson.annotations.SerializedName

@Entity(tableName = "single_film_table")
data class SingleFilmServerModel(
    @SerializedName("kinopoiskId")
    @PrimaryKey
    val filmId: Int,

//    @SerializedName( "imdbId")
//    val imdbID: Any? = null,

    @SerializedName("nameRu")
    val name: String,
//    val nameEn: Any? = null,
//    val nameOriginal: Any? = null,

    @SerializedName("posterUrl")
    @ColumnInfo(name = "poster_URL")
    val posterURL: String,

    @SerializedName("posterUrlPreview")
    @ColumnInfo(name = "poster_URL_preview")
    val posterURLPreview: String,

    val year: Int,
    val description: String,
    val countries: List<Country>,
    val genres: List<Genre>,
) {
    fun toItem() = CommonFilmsItem(
        filmId,
        posterURLPreview,
        name,
        description,
        genres[0].genre,
        countries[0].country,
        year.toString()
    )
}
