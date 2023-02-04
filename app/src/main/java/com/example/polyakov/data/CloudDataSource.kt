package com.example.polyakov.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CloudDataSource : CloudDataSourceInt {

    var callbackSuccessList: ((MutableList<Film>) -> Unit)? = null
    var callbackError: ((Unit?) -> Unit)? = null
    var callbackSuccessFilm: ((FullFilmDescServerModel) -> Unit)? = null


    override fun getFilms() {
        Common.filmListService.getMovieList().enqueue(object : Callback<FilmServerModel> {
            override fun onResponse(
                call: Call<FilmServerModel>,
                response: Response<FilmServerModel>
            ) {
                if (response.isSuccessful) {
                    callbackSuccessList?.invoke(response.body()!!.films.toMutableList())
                } else {
                    callbackError
                }
            }

            override fun onFailure(call: Call<FilmServerModel>, t: Throwable) {
                callbackError
            }
        })
    }

    override fun getFilm(id: Int) {
        Common.filmListService.getFilm(id).enqueue(object : Callback<FullFilmDescServerModel> {
            override fun onResponse(
                call: Call<FullFilmDescServerModel>,
                response: Response<FullFilmDescServerModel>
            ) {
                if (response.isSuccessful) {
                    callbackSuccessFilm?.invoke(response.body()!!)
                } else {
                    callbackError
                }
            }

            override fun onFailure(call: Call<FullFilmDescServerModel>, t: Throwable) {
                callbackError
            }
        })
    }
}