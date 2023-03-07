package com.example.polyakov.domain

import com.example.polyakov.data.FilmsRepository
import javax.inject.Inject

class BaseInteractor @Inject constructor(private val repository: FilmsRepository) {

    suspend fun getFilmsList(): MutableList<CommonFilmsItem> {
        return repository.getFilmsDB()
    }

    suspend fun getSingleFilm(id: Int): CommonFilmsItem {
        return repository.getSingleFilmByIdDB(id)
    }
}