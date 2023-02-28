package com.example.polyakov.domain

data class CommonFilmsItem(
    val id: Int,
    val posterURL: String,
    val description: String = "",
    val filmName: String,
    val genres: String,
    val countries: String = "",
    val year: String
) {

}