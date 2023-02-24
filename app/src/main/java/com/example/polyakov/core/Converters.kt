package com.example.polyakov.core

import androidx.room.TypeConverter
import com.example.polyakov.model.entities.Country
import com.example.polyakov.model.entities.Genre

class Converters {

    @TypeConverter
    fun genresToString(list: List<Genre>): String {
        var genreString = ""
        for (i in list.indices) {
            genreString += if (i == list.size - 1) list[i].genre
            else "${list[i].genre}, "
        }
        return genreString
    }

    @TypeConverter
    fun stringToGenres(string: String): List<Genre> {
        return listOf(Genre(string))
    }

    @TypeConverter
    fun countriesToString(list: List<Country>): String {
        var countriesString = ""
        for (i in list.indices) {
            countriesString += if (i == list.size - 1) list[i].country
            else "${list[i].country}, "
        }
        return countriesString
    }

    @TypeConverter
    fun stringToCountries(string: String): List<Country> {
        return listOf(Country(string))
    }
}