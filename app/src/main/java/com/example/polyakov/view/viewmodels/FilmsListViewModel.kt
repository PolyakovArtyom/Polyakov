package com.example.polyakov.view.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polyakov.domain.BaseInteractor
import com.example.polyakov.domain.CommonFilmsItem
import com.example.polyakov.view.communications.CommunicationFilms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmsListViewModel @Inject constructor(
    private val communication: CommunicationFilms,
    private val interactor: BaseInteractor
) : ViewModel() {

    fun filmsListLiveData() =
        viewModelScope.launch(Dispatchers.IO) {
            val filmsList = interactor.getFilmsList()
            withContext(Dispatchers.Main) {
                communication.updateList(filmsList)
            }
        }

    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonFilmsItem>>) {
        communication.observeList(owner, observer)
    }
}