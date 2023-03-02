package com.example.polyakov.view.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.polyakov.R
import com.example.polyakov.domain.CommonFilmsItem

class FilmsViewHolder(private val view: View): ViewHolder(view) {
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