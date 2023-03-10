package com.example.polyakov.data.model.daos

import androidx.room.*
import com.example.polyakov.data.model.entities.Films

@Dao
interface FilmsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(films: Films)

    @Query("SELECT * FROM films_table")
    fun getFilmsDB(): List<Films>
}