package com.example.polyakov.view.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polyakov.domain.BaseInteractor
import com.example.polyakov.domain.CommonFilmsItem
import com.example.polyakov.view.communications.CommunicationSingleFilm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SingleFilmVIewModel @Inject constructor(
    private val communication: CommunicationSingleFilm,
    private val interactor: BaseInteractor
) : ViewModel() {
//    @Inject
//    lateinit var interactor: BaseInteractor
//    @Inject
//    lateinit var communication: Communication

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