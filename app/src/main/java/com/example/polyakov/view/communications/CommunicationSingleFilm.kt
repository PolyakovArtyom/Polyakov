package com.example.polyakov.view.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.polyakov.domain.CommonFilmsItem
import javax.inject.Inject

class CommunicationSingleFilm @Inject constructor() {
    private val singleLiveData = MutableLiveData<CommonFilmsItem>()

    fun updateSingleFilm(film: CommonFilmsItem) {
        singleLiveData.value = film
    }

    fun observeFilm(owner: LifecycleOwner, observer: Observer<CommonFilmsItem>) {
        singleLiveData.observe(owner, observer)
    }
}