package com.example.polyakov

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.polyakov.data.CloudDataSource
import com.example.polyakov.data.Common
import com.example.polyakov.data.Film
import com.example.polyakov.data.FilmServerModel
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularFilmsFragment : Fragment() {

    private lateinit var adapterRV: FilmsAdapterRV
    var onCardClick: ((Film) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shimmer = view.findViewById<View>(R.id.shimmerScreen)
        shimmer.isVisible = true

        val errorScr: LinearLayout = view.findViewById(R.id.errorScreen)
        val errorBtn = errorScr.findViewById<MaterialButton>(R.id.retryBtn)

        val recyclerView = view.findViewById<RecyclerView>(R.id.popularFilmsRecyclerView)
        val cloudDataSource = CloudDataSource()
        adapterRV = FilmsAdapterRV()

        recyclerView.adapter = adapterRV
        adapterRV.onCardClick = { film ->
            onCardClick?.invoke(film)
        }

        cloudDataSource.callbackSuccessList = {
            adapterRV.list = it
            shimmer.isVisible = false
        }
        cloudDataSource.callbackError = {
            errorScr.isVisible = true
            recyclerView.isVisible = false
        }
        cloudDataSource.getFilms()

        errorBtn.setOnClickListener {
            errorScr.isVisible = false
            recyclerView.isVisible = true
            cloudDataSource.getFilms()
        }
    }
}