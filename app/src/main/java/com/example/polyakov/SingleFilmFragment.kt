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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleFilmFragment(private val film: Film) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val error = view.findViewById<View>(R.id.singleFilmError)
        val errorBtn = view.findViewById<Button>(R.id.retryBtn)
        errorBtn.setOnClickListener {
            view.findViewById<View>(R.id.card_root).visibility = View.VISIBLE
            error.visibility = View.VISIBLE
        }

        val dataSource = CloudDataSource()
        dataSource.callbackSuccessFilm = { fullFilm ->
            val imageView = view.findViewById<ImageView>(R.id.cardImage)
            Glide.with(requireContext()).load(film.posterURL).into(imageView)
            val filmName = view.findViewById<TextView>(R.id.filmName)
            filmName.text = film.nameRu
            val description = view.findViewById<TextView>(R.id.filmDescription)
            description.text = fullFilm.description
            val genres = view.findViewById<TextView>(R.id.filmGenres)
            genres.text = film.genres[0].genre
            val countries = view.findViewById<TextView>(R.id.filmCountries)
            countries.text = film.countries[0].country
            val year = view.findViewById<TextView>(R.id.filmYear)
            year.text = film.year
        }
        dataSource.callbackError = {
            view.findViewById<View>(R.id.card_root).visibility = View.INVISIBLE
            error.isVisible = true
            dataSource.getFilm(film.filmID)
        }
        dataSource.getFilm(film.filmID)
    }
}