package com.example.polyakov.view.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.polyakov.App
import com.example.polyakov.domain.BaseInteractor
import com.example.polyakov.domain.CommonFilmsItem
import com.example.polyakov.view.Communication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SingleFilmVIewModel(application: App) : AndroidViewModel(application) {
    private val interactor = BaseInteractor.getInstance(application)
    private val communication: Communication = Communication()

    fun singleFilmLiveData(filmId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val singleFilm = interactor.getSingleFilm(filmId)
        withContext(Dispatchers.Main) {
            communication.updateSingleFilm(singleFilm)
        }
    }

    fun observeFilm(owner: LifecycleOwner, observer: Observer<CommonFilmsItem>) {
        communication.observeFilm(owner, observer)
    }
}