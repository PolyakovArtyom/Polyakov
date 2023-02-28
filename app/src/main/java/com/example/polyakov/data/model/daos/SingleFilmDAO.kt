package com.example.polyakov.data.model.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.polyakov.data.model.entities.SingleFilmServerModel

@Dao
interface SingleFilmDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(singleFilmServerModel: SingleFilmServerModel)

    @Query("SELECT * FROM single_film_table WHERE filmId = :filmId")
    fun getSingleFilmByIdDB(filmId: Int): SingleFilmServerModel
}