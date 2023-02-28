package com.example.polyakov.data

import android.app.Application
import android.util.Log
import com.example.polyakov.data.api.FilmListService
import com.example.polyakov.data.api.RetrofitInstance
import com.example.polyakov.data.model.FilmsRoomDB
import com.example.polyakov.data.model.entities.Films
import com.example.polyakov.domain.CommonFilmsItem
import com.example.polyakov.domain.TAG

class FilmsRepository private constructor(application: Application) {

    private val filmsDAO = FilmsRoomDB.getDataBase(application).getFilmsDao()
    private val singleFilmDAO = FilmsRoomDB.getDataBase(application).getSingleFilmDao()
    private val filmsCalls = RetrofitInstance.getInstance().create(FilmListService::class.java)

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
        return singleFilmDAO.getSingleFilmByIdDB(filmId).toItem().also {
            insertSingleFilm(filmId)
        }
    }

    private suspend fun insertSingleFilm(id: Int) {
        try {
            val singleFilm = filmsCalls.getFilm(id)
            singleFilmDAO.insertFilms(singleFilm)
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    companion object {
        private var INSTANCE: FilmsRepository? = null

        fun getInstance(application: Application): FilmsRepository = INSTANCE ?: kotlin.run {
            INSTANCE = FilmsRepository(application = application)
            INSTANCE!!
        }
    }
}