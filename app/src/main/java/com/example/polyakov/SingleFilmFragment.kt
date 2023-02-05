package com.example.polyakov

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.polyakov.data.*

class SingleFilmFragment(private val film: Film) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataSource = CloudDataSource()
        val error = view.findViewById<View>(R.id.singleFilmError)
        val errorBtn = view.findViewById<Button>(R.id.retryBtn)
        errorBtn.setOnClickListener {
            view.findViewById<View>(R.id.card_root).visibility = View.VISIBLE
            error.visibility = View.VISIBLE
            dataSource.getFilm(film.filmID)
        }

        val imageView = view.findViewById<ImageView>(R.id.cardImage)
        val filmName = view.findViewById<TextView>(R.id.filmName)
        val description = view.findViewById<TextView>(R.id.filmDescription)
        val genres = view.findViewById<TextView>(R.id.filmGenres)
        val countries = view.findViewById<TextView>(R.id.filmCountries)
        val year = view.findViewById<TextView>(R.id.filmYear)

        dataSource.callbackSuccessFilm = { fullFilm ->
            Glide.with(requireContext()).load(fullFilm.posterURL).into(imageView)
            filmName.text = fullFilm.nameRu
            description.text = fullFilm.description
            var genresText = ""
            for (i in 0 until fullFilm.genres.size) {
                genresText += if (i == fullFilm.genres.size - 1) film.genres[i].genre
                else "${fullFilm.genres[i].genre}, "
            }
            genres.text = fullFilm.genres[0].genre
            var countriesText = ""
            for (i in 0 until fullFilm.countries.size) {
                countriesText += if (i == fullFilm.countries.size - 1) film.countries[i].country
                else "${fullFilm.countries[i].country}, "
            }
            countries.text = countriesText
            year.text = fullFilm.year.toString()
        }

        dataSource.callbackError = {
            view.findViewById<View>(R.id.card_root).visibility = View.INVISIBLE
            error.isVisible = true
        }

        dataSource.getFilm(film.filmID)
    }
}