package com.example.polyakov.data

interface CloudDataSourceInt {
    fun getFilms()
    fun getFilm(id: Int)
}