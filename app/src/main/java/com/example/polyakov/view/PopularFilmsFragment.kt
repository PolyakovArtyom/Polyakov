package com.example.polyakov.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.polyakov.R
import com.example.polyakov.viewmodels.FilmsListViewModel

class PopularFilmsFragment : Fragment() {

    private val filmsListViewModel by viewModels<FilmsListViewModel>()
    private lateinit var adapterRV: FilmsAdapterRV
//    var onCardClick: ((Film) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val shimmer = view.findViewById<View>(R.id.shimmerScreen)
//        shimmer.isVisible = true

//        val errorScr: LinearLayout = view.findViewById(R.id.errorScreen)
//        val errorBtn = errorScr.findViewById<MaterialButton>(R.id.retryBtn)

        val recyclerView = view.findViewById<RecyclerView>(R.id.popularFilmsRecyclerView)
        adapterRV = FilmsAdapterRV()
        recyclerView.adapter = adapterRV

        filmsListViewModel.filmsListLiveData.observe(viewLifecycleOwner) {
            adapterRV.list = it.toMutableList()
        }

//        val cloudDataSource = CloudDataSource()
//        adapterRV.onCardClick = { film ->
//            onCardClick?.invoke(film)
//        }
//
//        cloudDataSource.callbackSuccessList = {
//            adapterRV.list = it
//            shimmer.isVisible = false
//        }
//        cloudDataSource.callbackError = {
//            errorScr.isVisible = true
//            recyclerView.isVisible = false
//        }
//        cloudDataSource.getFilms()
//
//        errorBtn.setOnClickListener {
//            errorScr.isVisible = false
//            recyclerView.isVisible = true
//            cloudDataSource.getFilms()
//        }
    }
}