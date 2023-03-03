package com.example.polyakov.data

import android.util.Log
import com.example.polyakov.data.api.FilmListService
import com.example.polyakov.data.model.daos.FilmsDAO
import com.example.polyakov.data.model.daos.SingleFilmDAO
import com.example.polyakov.data.model.entities.Films
import com.example.polyakov.domain.CommonFilmsItem
import com.example.polyakov.domain.TAG
import javax.inject.Inject

class FilmsRepository @Inject constructor(
    private val filmsDAO: FilmsDAO,
    private val singleFilmDAO: SingleFilmDAO,
    private val filmsCalls: FilmListService
) {

    private fun insertFilms(films: Films) {
        filmsDAO.insertFilms(films)
    }

    suspend fun getFilmsDB(): MutableList<CommonFilmsItem> {
        val serverList = filmsDAO.getFilmsDB()
        val itemList = mutableListOf<CommonFilmsItem>()
        serverList.forEach { films ->
            itemList.add(films.toItem())
        }
        return itemList.also {
            getFilmsFromRemote()
        }
    }

    private suspend fun getFilmsFromRemote() {
        try {
            val filmsList = filmsCalls.getMovieList()
            filmsList.films.forEach { films ->
                insertFilms(films)
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    suspend fun getSingleFilmByIdDB(filmId: Int): CommonFilmsItem {
        return singleFilmDAO.getSingleFilmByIdDB(filmId).toItem().also { insertSingleFilm(filmId) }
    }

    private suspend fun insertSingleFilm(id: Int) {
        try {
            val singleFilm = filmsCalls.getFilm(id)
            singleFilmDAO.insertFilms(singleFilm)
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }
}