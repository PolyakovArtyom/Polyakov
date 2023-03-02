package com.example.polyakov.di.components

import com.example.polyakov.data.FilmsRepository
import com.example.polyakov.data.api.FilmListService
import com.example.polyakov.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    val retrofitApi: FilmListService
    fun inject(repository: FilmsRepository)
}