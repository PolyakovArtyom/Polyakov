package com.example.polyakov

import android.app.Application
import com.example.polyakov.di.components.AppComponent
import com.example.polyakov.di.components.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}