package com.example.polyakov.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.polyakov.App
import com.example.polyakov.R
import com.example.polyakov.databinding.FragmentPopularFilmsBinding
import com.example.polyakov.view.adapters.FilmsAdapterRV
import com.example.polyakov.view.viewmodels.FilmsListViewModel
import com.example.polyakov.view.viewmodels.ViewModelFactory
import javax.inject.Inject

class PopularFilmsFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var viewModel: FilmsListViewModel

    private var adapterRV: FilmsAdapterRV? = null
    private var _binding: FragmentPopularFilmsBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        viewModel = factory.create(FilmsListViewModel::class.java)
        super.onAttach(context)
    }

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
        _binding = null
    }

    companion object {
        const val FILM_ID = "FILM_ID"
    }
}