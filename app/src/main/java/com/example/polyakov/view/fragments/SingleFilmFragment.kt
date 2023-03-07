package com.example.polyakov.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.polyakov.App
import com.example.polyakov.databinding.FragmentSingleFilmBinding
import com.example.polyakov.view.viewmodels.SingleFilmVIewModel
import com.example.polyakov.view.viewmodels.ViewModelFactory
import javax.inject.Inject

class SingleFilmFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var viewModel: SingleFilmVIewModel

    private var _binding: FragmentSingleFilmBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        viewModel = factory.create(SingleFilmVIewModel::class.java)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleFilmBinding.inflate(inflater, container, false)
        return binding.root
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
        _binding = null
    }

    companion object {
        const val FILM_ID = "FILM_ID"
    }
}