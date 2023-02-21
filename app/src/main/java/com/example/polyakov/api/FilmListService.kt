package com.example.polyakov.api

import com.example.polyakov.model.entities.FilmsServerModel
import com.example.polyakov.model.entities.SingleFilmServerModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FilmListService {
    @GET("top?type=TOP_100_POPULAR_FILMS&page=1")
    @Headers("X-API-KEY: " + "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    suspend fun getMovieList(): FilmsServerModel

    @GET("{id}")
    @Headers("X-API-KEY: " + "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    suspend fun getFilm(@Path("id") filmId: Int): SingleFilmServerModel
}