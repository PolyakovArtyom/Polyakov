package com.example.polyakov.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.polyakov.model.entities.SingleFilmServerModel

@Dao
interface SingleFilmDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(singleFilmServerModel: SingleFilmServerModel)

    @Query("SELECT * FROM single_film_table WHERE filmId = :filmId")
    fun getSingleFilmByIdLiveData(filmId: Int): LiveData<SingleFilmServerModel>
}