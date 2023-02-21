package com.example.polyakov.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.polyakov.R

class MainActivity : AppCompatActivity() {

    private var isActivated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filmsFragment = PopularFilmsFragment()
//        filmsFragment.onCardClick = {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.popularFilmsFragment, SingleFilmFragment(it))
//                .addToBackStack("tag")
//                .commit()
//        }
        supportFragmentManager.beginTransaction()
            .add(R.id.popularFilmsFragment, filmsFragment, "tag")
            .commit()
        isActivated = true
    }
}