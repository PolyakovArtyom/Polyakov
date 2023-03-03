package com.example.polyakov.view.viewmodels

import androidx.lifecycle.*
import com.example.polyakov.domain.BaseInteractor
import com.example.polyakov.domain.CommonFilmsItem
import com.example.polyakov.view.Communication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmsListViewModel : ViewModel() {
    @Inject
    lateinit var communication: Communication
    @Inject
    lateinit var interactor: BaseInteractor

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