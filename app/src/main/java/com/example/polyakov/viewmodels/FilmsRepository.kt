package com.example.polyakov.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.polyakov.api.FilmListService
import com.example.polyakov.api.RetrofitInstance
import com.example.polyakov.model.FilmsRoomDB
import com.example.polyakov.model.entities.Films
import com.example.polyakov.model.entities.SingleFilmServerModel

class FilmsRepository private constructor(application: Application) {

    private val filmsDAO = FilmsRoomDB.getDataBase(application).getFilmsDao()
    private val singleFilmDAO = FilmsRoomDB.getDataBase(application).getSingleFilmDao()
    private val filmsCalls = RetrofitInstance.getInstance().create(FilmListService::class.java)

    private fun insertFilms(films: Films) {
        filmsDAO.insertFilms(films)
    }

    suspend fun getFilmsLiveData(): LiveData<List<Films>> {
        return filmsDAO.getFilmsLiveData().also {
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

    suspend fun getSingleFilmLiveData(filmId: Int): LiveData<SingleFilmServerModel> {
        return singleFilmDAO.getSingleFilmByIdLiveData(filmId).also {
            try {
                val singleFilm = filmsCalls.getFilm(filmId)
                singleFilmDAO.insertFilms(singleFilm)
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    companion object {
        private const val TAG = "Tag"
        private var INSTANCE: FilmsRepository? = null

        fun getInstance(application: Application): FilmsRepository = INSTANCE ?: kotlin.run {
            INSTANCE = FilmsRepository(application = application)
            INSTANCE!!
        }
    }
}