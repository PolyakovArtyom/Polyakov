package com.example.polyakov.data

object Common {
    private val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"
    val filmListService: FilmListService
        get() = RetrofitClient.getClient(BASE_URL).create(FilmListService::class.java)
}