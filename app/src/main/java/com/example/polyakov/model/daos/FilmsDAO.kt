package com.example.polyakov.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.polyakov.model.entities.Films

//import com.example.polyakov.model.entities.Films

@Dao
interface FilmsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(films: Films)

    @Query("SELECT * FROM films_table")
    fun getFilmsLiveData(): LiveData<List<Films>>
}