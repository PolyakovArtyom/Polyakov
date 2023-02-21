package com.example.polyakov.model.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "single_film_table")
data class SingleFilmServerModel (
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
//
//    @SerializedName("coverUrl")
//    val coverURL: Any? = null,
//
//    @SerializedName("logoUrl")
//    val logoURL: Any? = null,
//
//    val reviewsCount: Int,
//    val ratingGoodReview: Any? = null,
//    val ratingGoodReviewVoteCount: Int,
//    val ratingKinopoisk: Double,
//    val ratingKinopoiskVoteCount: Int,
//    val ratingImdb: Any? = null,
//    val ratingImdbVoteCount: Int,
//    val ratingFilmCritics: Any? = null,
//    val ratingFilmCriticsVoteCount: Int,
//    val ratingAwait: Int,
//    val ratingAwaitCount: Int,
//
//    @SerializedName("ratingRfCritics")
//    val ratingRFCritics: Any? = null,
//
//    @SerializedName("ratingRfCriticsVoteCount")
//    val ratingRFCriticsVoteCount: Int,
//
//    @SerializedName("webUrl")
//    val webURL: String,

    val year: Int,
//    val filmLength: Int,
//    val slogan: Any? = null,
    val description: String,
//    val shortDescription: Any? = null,
//    val editorAnnotation: Any? = null,
//    val isTicketsAvailable: Boolean,
//    val productionStatus: Any? = null,
//    val type: String,
//
//    @SerializedName("ratingMpaa")
//    val ratingMPAA: Any? = null,
//
//    val ratingAgeLimits: String,
//    val countries: List<Country>,
//    val genres: List<Genre>,
//    val startYear: Any? = null,
//    val endYear: Any? = null,
//    val serial: Boolean,
//    val shortFilm: Boolean,
//    val completed: Boolean,
//    val hasImax: Boolean,
//    val has3D: Boolean,
//    val lastSync: String
)
