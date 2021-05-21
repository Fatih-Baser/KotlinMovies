package com.fatihbaser.navigationkotlin2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fatihbaser.navigationkotlin2.models.home.MovieResult


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieResult?)

    @Delete
    fun deleteMovie(movie: MovieResult?)

    @Query("SELECT * FROM movies")
    fun getAllMovies():LiveData<List<MovieResult>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getSingleMovie(movieId:Int):LiveData<MovieResult>

}