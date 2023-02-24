package com.example.polyakov.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.polyakov.model.entities.Films
import kotlinx.coroutines.Dispatchers

class FilmsListViewModel(application: Application) : AndroidViewModel(application) {
    private val filmsRepository = FilmsRepository.getInstance(application)

    fun filmsListLiveData(): LiveData<List<Films>> = liveData(Dispatchers.IO) {
        emitSource(filmsRepository.getFilmsLiveData())
    }
}