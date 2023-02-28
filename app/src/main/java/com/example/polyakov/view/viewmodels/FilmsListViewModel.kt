package com.example.polyakov.view.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.polyakov.domain.BaseInteractor
import com.example.polyakov.domain.CommonFilmsItem
import com.example.polyakov.view.Communication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmsListViewModel(application: Application) : AndroidViewModel(application) {
    private val interactor = BaseInteractor.getInstance(application)
    private val communication: Communication = Communication()

    fun filmsListLiveData() =
        viewModelScope.launch(Dispatchers.IO) {
            val filmsList = interactor.getFilmsList()
            withContext(Dispatchers.Main) {
                communication.getList(filmsList)
            }
        }

    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonFilmsItem>>) {
        communication.observeList(owner, observer)
    }
}