package com.example.polyakov.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.polyakov.App
import com.example.polyakov.R
import com.example.polyakov.databinding.FragmentSingleFilmBinding
import com.example.polyakov.view.viewmodels.SingleFilmVIewModel
import com.example.polyakov.view.viewmodels.ViewModelFactory
import javax.inject.Inject

class SingleFilmFragment : Fragment(R.layout.fragment_single_film) {

    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var viewModel: SingleFilmVIewModel

    private val binding by viewBinding(FragmentSingleFilmBinding::bind)

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        viewModel = factory.create(SingleFilmVIewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            viewModel.singleFilmLiveData(bundle.getInt(FILM_ID))
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.observeFilm(this) { film ->
            Glide.with(requireContext()).load(film.posterURL).into(binding.cardImage)
            binding.filmName.text = film.filmName
            binding.filmDescription.text = film.description
            binding.filmYear.text = film.year
            binding.filmGenres.text = film.genres
            binding.filmCountries.text = film.countries
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        const val FILM_ID = "FILM_ID"
    }
}