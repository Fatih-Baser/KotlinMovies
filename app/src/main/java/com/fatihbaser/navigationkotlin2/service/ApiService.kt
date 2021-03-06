package com.fatihbaser.navigationkotlin2.service


import com.fatihbaser.navigationkotlin2.models.detail.MovieDetailResponse
import com.fatihbaser.navigationkotlin2.models.home.MovieResponse
import com.fatihbaser.navigationkotlin2.models.search.MovieSearchResponse
import com.fatihbaser.navigationkotlin2.models.videos.MovieVideoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(): Single<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Single<MovieResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(): Single<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") movieId: Int): Single<MovieDetailResponse>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") movieId: Int): Single<MovieVideoResponse>

    @GET("search/movie")
    fun getSearchMovies(@Query("query") search: String): Single<MovieSearchResponse>

}