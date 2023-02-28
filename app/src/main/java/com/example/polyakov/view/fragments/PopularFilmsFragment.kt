package com.example.polyakov.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.polyakov.R
import com.example.polyakov.databinding.FragmentPopularFilmsBinding
import com.example.polyakov.view.adapters.FilmsAdapterRV
import com.example.polyakov.view.viewmodels.FilmsListViewModel

class PopularFilmsFragment : Fragment() {

    private val viewModel by viewModels<FilmsListViewModel>()
    private var adapterRV: FilmsAdapterRV? = null
    private var _binding: FragmentPopularFilmsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createAdapter()
        binding.popularFilmsRecyclerView.adapter = adapterRV
        viewModelObserve()
    }

    private fun createAdapter() {
        adapterRV = FilmsAdapterRV { filmId ->
            val bundle = bundleOf("FILM_ID" to filmId)
            findNavController().navigate(R.id.action_nav_graph_to_singleFilmFrag, bundle)
        }
    }

    private fun viewModelObserve() {
        viewModel.observeList(this) {
            adapterRV!!.list = it.toMutableList()
        }
        viewModel.filmsListLiveData()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapterRV = null
        _binding = null
    }
}