package com.example.polyakov.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"
    private var INSTANCE: Retrofit? = null

    fun getInstance(): Retrofit = INSTANCE ?: kotlin.run {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    val filmListService: FilmListService
//        get() = RetrofitClient.getClient(BASE_URL).create(FilmListService::class.java)
}