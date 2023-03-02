package com.example.polyakov.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.polyakov.R
import com.example.polyakov.domain.CommonFilmsItem

class FilmsAdapterRV(private val onCardClick: (Int) -> Unit) :
    RecyclerView.Adapter<FilmsViewHolder>() {

    var list: MutableList<CommonFilmsItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return FilmsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.filmCard.setOnClickListener {
            onCardClick.invoke(list[position].id)
        }
    }

    override fun getItemCount(): Int = list.size
}