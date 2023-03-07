package com.example.polyakov.data.di.modules

import androidx.lifecycle.ViewModel
import com.example.polyakov.view.viewmodels.FilmsListViewModel
import com.example.polyakov.view.viewmodels.SingleFilmVIewModel
import com.example.polyakov.view.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface AppBindsModule {

    @Binds
    @Singleton
    @[IntoMap ViewModelKey(FilmsListViewModel::class)]
    fun provideFilmsViewModule(filmsListViewModel: FilmsListViewModel): ViewModel

    @Binds
    @Singleton
    @[IntoMap ViewModelKey(SingleFilmVIewModel::class)]
    fun provideSingleFilmViewModel(singleFilmVIewModel: SingleFilmVIewModel): ViewModel
}