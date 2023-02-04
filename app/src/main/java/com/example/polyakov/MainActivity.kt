package com.example.polyakov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filmsFragment = PopularFilmsFragment()
        filmsFragment.onCardClick = {
            supportFragmentManager.beginTransaction()
                .replace(R.id.popularFilmsFragment, SingleFilmFragment(it))
                .addToBackStack("tag")
                .commit()
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.popularFilmsFragment, filmsFragment, "tag")
            .commit()
    }
}