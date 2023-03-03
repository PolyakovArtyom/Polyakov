package com.example.polyakov.view

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.polyakov.domain.CommonFilmsItem
import javax.inject.Inject

class Communication @Inject constructor() {
    private val listLiveData = MutableLiveData<List<CommonFilmsItem>>()
    private val singleLiveData = MutableLiveData<CommonFilmsItem>()

    fun updateList(list: List<CommonFilmsItem>) {
        listLiveData.value = list
    }

    fun updateSingleFilm(film: CommonFilmsItem) {
        singleLiveData.value = film
    }

    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonFilmsItem>>) {
        listLiveData.observe(owner, observer)
    }

    fun observeFilm(owner: LifecycleOwner, observer: Observer<CommonFilmsItem>) {
        singleLiveData.observe(owner, observer)
    }
}