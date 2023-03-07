package com.example.polyakov.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.polyakov.App
import com.example.polyakov.R
import com.example.polyakov.databinding.FragmentPopularFilmsBinding
import com.example.polyakov.view.adapters.FilmsAdapterRV
import com.example.polyakov.view.viewmodels.FilmsListViewModel
import com.example.polyakov.view.viewmodels.ViewModelFactory
import javax.inject.Inject

class PopularFilmsFragment : Fragment(R.layout.fragment_popular_films) {

    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var viewModel: FilmsListViewModel

    private var adapterRV: FilmsAdapterRV? = null
    private val binding by viewBinding (FragmentPopularFilmsBinding::bind)

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        viewModel = factory.create(FilmsListViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createAdapter()
        viewModelObserve()
        viewModel.filmsListLiveData()
    }

    private fun createAdapter() {
        adapterRV = FilmsAdapterRV { filmId ->
            val bundle = bundleOf(FILM_ID to filmId)
            findNavController().navigate(R.id.action_nav_graph_to_singleFilmFrag, bundle)
        }
        binding.popularFilmsRecyclerView.adapter = adapterRV
    }

    private fun viewModelObserve() {
        viewModel.observeList(this) {
            adapterRV?.list = it.toMutableList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapterRV = null
    }

    companion object {
        const val FILM_ID = "FILM_ID"
    }
}