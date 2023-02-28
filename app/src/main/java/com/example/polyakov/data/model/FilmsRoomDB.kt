package com.example.polyakov.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.polyakov.data.model.daos.FilmsDAO
import com.example.polyakov.data.model.daos.SingleFilmDAO
import com.example.polyakov.data.model.entities.Films
import com.example.polyakov.data.model.entities.SingleFilmServerModel

@Database(entities = [Films::class, SingleFilmServerModel::class], version = 4)
@TypeConverters(Converters::class)
abstract class FilmsRoomDB: RoomDatabase() {

    abstract fun getFilmsDao(): FilmsDAO

    abstract fun getSingleFilmDao(): SingleFilmDAO

    companion object{
        private var INSTANCE: FilmsRoomDB? = null

        fun getDataBase(context: Context) = INSTANCE ?: kotlin.run {
            Room.databaseBuilder(
                context.applicationContext,
                FilmsRoomDB::class.java,
                "Films_data_base"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}