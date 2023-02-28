package com.example.polyakov.domain

import android.app.Application
import com.example.polyakov.data.FilmsRepository

class BaseInteractor(application: Application) {

    private val repository = FilmsRepository.getInstance(application)
    //    private val liveDataFilms = MutableLiveData<MutableList<CommonFilmsItem>>()
    //    private val liveDataSingleFilm = MutableLiveData<CommonFilmsItem>()

    suspend fun getFilmsList(): MutableList<CommonFilmsItem> {
        return repository.getFilmsDB()
    }

    suspend fun getSingleFilm(id: Int): CommonFilmsItem {
        return repository.getSingleFilmByIdDB(id)
    }

    companion object {
        private var INSTANCE: BaseInteractor? = null

        fun getInstance(application: Application): BaseInteractor = INSTANCE ?: kotlin.run {
            INSTANCE = BaseInteractor(application)
            INSTANCE!!
        }
    }
}

const val TAG = "Tag"