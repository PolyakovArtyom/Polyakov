package com.example.polyakov.data.di.components

import android.content.Context
import com.example.polyakov.data.di.modules.AppBindsModule
import com.example.polyakov.data.di.modules.AppModule
import com.example.polyakov.view.fragments.PopularFilmsFragment
import com.example.polyakov.view.fragments.SingleFilmFragment
import com.example.polyakov.view.viewmodels.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, AppBindsModule::class])
@Singleton
interface AppComponent {

    val factory: ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(popularFilmsFragment: PopularFilmsFragment)
    fun inject(singleFilmFragment: SingleFilmFragment)
}