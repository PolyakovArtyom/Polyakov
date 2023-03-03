package com.example.polyakov.data.di.components

import android.content.Context
import com.example.polyakov.data.di.modules.AppModule
import com.example.polyakov.view.viewmodels.FilmsListViewModel
import com.example.polyakov.view.viewmodels.SingleFilmVIewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(filmsListViewModel: FilmsListViewModel)
    fun inject(singleFilmVIewModel: SingleFilmVIewModel)
}