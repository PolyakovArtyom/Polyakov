package com.example.polyakov.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.polyakov.R
import com.example.polyakov.domain.CommonFilmsItem

class FilmsAdapterRV(private val onCardClick : (Int) -> Unit): RecyclerView.Adapter<FilmsAdapterRV.FilmViewHolder>() {

    var list: MutableList<CommonFilmsItem> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
//    var onCardClick: ((Film) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(list[position])
        holder.filmCard.setOnClickListener {
            onCardClick.invoke(list[position].id)
        }
    }

    override fun getItemCount(): Int = list.size

    class FilmViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val filmCard: ConstraintLayout = itemView.findViewById(R.id.card_root)
        private val filmPoster: ImageView = itemView.findViewById(R.id.cardImage)
        private val filmTitle: TextView = itemView.findViewById(R.id.cardTitle)
        private val genresAndYear: TextView = itemView.findViewById(R.id.cardBody)

        fun bind(film: CommonFilmsItem) {
            Glide.with(filmPoster.context).load(film.posterURL).into(filmPoster)
            filmTitle.text = film.filmName
            val genresAndYearText = film.genres + " (" + film.year + ")"
            genresAndYear.text = genresAndYearText
        }
    }
}