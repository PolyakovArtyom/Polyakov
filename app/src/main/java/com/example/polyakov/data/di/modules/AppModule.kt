package com.example.polyakov.data.di.modules

import android.content.Context
import androidx.room.Room
import com.example.polyakov.data.api.FilmListService
import com.example.polyakov.data.model.FilmsRoomDB
import com.example.polyakov.data.model.daos.FilmsDAO
import com.example.polyakov.data.model.daos.SingleFilmDAO
import com.example.polyakov.utils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): FilmListService {
        return retrofit.create(FilmListService::class.java)
    }

    @Provides
    @Singleton
    fun provideFilmsDatabase(context: Context): FilmsRoomDB {
        return Room.databaseBuilder(
            context.applicationContext,
            FilmsRoomDB::class.java,
            "Films_data_base"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFilmsListDB(roomDB: FilmsRoomDB): FilmsDAO {
        return roomDB.getFilmsDao()
    }

    @Provides
    @Singleton
    fun provideSingleFilmByIDDB(roomDB: FilmsRoomDB): SingleFilmDAO {
        return roomDB.getSingleFilmDao()
    }
}