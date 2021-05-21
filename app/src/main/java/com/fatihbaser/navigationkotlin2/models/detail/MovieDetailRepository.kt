package com.fatihbaser.navigationkotlin2.models.detail

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.fatihbaser.navigationkotlin2.data.MovieDao
import com.fatihbaser.navigationkotlin2.data.MovieDatabase
import com.fatihbaser.navigationkotlin2.models.home.MovieResult


class MovieDetailRepository(context: Context) {

    private val db: MovieDatabase by lazy { MovieDatabase.getInstance(context) }
    private val dao: MovieDao by lazy { db.movieDao()}

    fun insertMovie(movieResult: MovieResult?){
        InsertAsyncTask(
            dao
        ).execute(movieResult)
    }

    fun deleteMovie(movieResult: MovieResult?){
        DeleteAsyncTask(
            dao
        ).execute(movieResult)
    }

    fun getAllMovies(): LiveData<List<MovieResult>> {
        return dao.getAllMovies()
    }

    fun getSingleMovie(movieId:Int?): LiveData<MovieResult> {
        return dao.getSingleMovie(movieId!!)
    }

    private class InsertAsyncTask(val dao: MovieDao): AsyncTask<MovieResult, Void, Void>(){
        override fun doInBackground(vararg params: MovieResult?): Void? {
            dao.insertMovie(params[0])
            return null
        }
    }

    private class DeleteAsyncTask(val dao: MovieDao): AsyncTask<MovieResult, Void, Void>(){
        override fun doInBackground(vararg params: MovieResult?): Void? {
            dao.deleteMovie(params[0])
            return null
        }
    }

}