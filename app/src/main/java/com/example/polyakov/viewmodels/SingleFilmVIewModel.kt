package com.example.polyakov.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.polyakov.model.entities.SingleFilmServerModel
import kotlinx.coroutines.Dispatchers

class SingleFilmVIewModel(application: Application) : AndroidViewModel(application) {
    private val filmsRepository = FilmsRepository.getInstance(application)

    fun singleFilmLiveData(filmId: Int): LiveData<SingleFilmServerModel> = liveData(Dispatchers.IO) {
        emitSource(filmsRepository.getSingleFilmLiveData(filmId))
    }
}