package com.example.polyakov

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.polyakov.data.Film

class FilmsAdapterRV: RecyclerView.Adapter<FilmsAdapterRV.FilmViewHolder>() {

    var list: MutableList<Film> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    var onCardClick: ((Film) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(list[position])
        holder.filmCard.setOnClickListener {
            onCardClick?.invoke(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    class FilmViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val filmCard: ConstraintLayout = itemView.findViewById(R.id.card_root)
        val filmPoster: ImageView = itemView.findViewById(R.id.cardImage)
        val filmTitle: TextView = itemView.findViewById(R.id.cardTitle)
        val genresAndYear: TextView = itemView.findViewById(R.id.cardBody)

        fun bind(film: Film) {
            Glide.with(filmPoster.context).load(film.posterURLPreview).into(filmPoster)
            filmTitle.text = film.nameRu
            val year = film.genres[0].genre + "(" + film.year + ")"
            genresAndYear.text = year
        }
    }
}