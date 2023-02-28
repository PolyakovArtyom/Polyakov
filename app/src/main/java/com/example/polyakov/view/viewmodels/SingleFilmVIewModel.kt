package com.example.polyakov.view.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.polyakov.domain.BaseInteractor
import com.example.polyakov.domain.CommonFilmsItem
import com.example.polyakov.view.Communication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SingleFilmVIewModel(application: Application) : AndroidViewModel(application) {
    private val interactor = BaseInteractor.getInstance(application)
    private val communication: Communication = Communication()

    fun singleFilmLiveData(filmId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val singleFilm = interactor.getSingleFilm(filmId)
        withContext(Dispatchers.Main) {
            communication.getSingleFilm(singleFilm)
        }
    }

    fun observeFilm(owner: LifecycleOwner, observer: Observer<CommonFilmsItem>) {
        communication.observeFilm(owner, observer)
    }
}