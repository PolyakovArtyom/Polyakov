package com.example.polyakov.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FilmListService {
    @GET("top?type=TOP_100_POPULAR_FILMS&page=1")
    @Headers("X-API-KEY: " + "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    fun getMovieList(): Call<FilmServerModel>

    @GET("{id}")
    @Headers("X-API-KEY: " + "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    fun getFilm(@Path("id") filmId: Int): Call<FullFilmDescServerModel>
}