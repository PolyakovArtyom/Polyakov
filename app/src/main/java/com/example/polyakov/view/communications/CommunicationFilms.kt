package com.example.polyakov.view.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.polyakov.domain.CommonFilmsItem
import javax.inject.Inject

class CommunicationFilms @Inject constructor() {
    private val listLiveData = MutableLiveData<List<CommonFilmsItem>>()

    fun updateList(list: List<CommonFilmsItem>) {
        listLiveData.value = list
    }

    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonFilmsItem>>) {
        listLiveData.observe(owner, observer)
    }
}