package com.fatihbaser.navigationkotlin2.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fatihbaser.navigationkotlin2.models.detail.MovieDetailRepository
import com.fatihbaser.navigationkotlin2.models.detail.MovieDetailResponse
import com.fatihbaser.navigationkotlin2.models.home.MovieResult
import com.fatihbaser.navigationkotlin2.models.videos.MovieVideoResponse
import com.fatihbaser.navigationkotlin2.models.videos.MovieVideoResult
import com.fatihbaser.navigationkotlin2.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieDetailRepository by lazy {
        MovieDetailRepository(
            application.applicationContext
        )
    }

    private val apiClient = ApiClient()
    private val disposable = CompositeDisposable()

    val movieDetails = MutableLiveData<MovieDetailResponse>()
    val movieTrailers = MutableLiveData<List<MovieVideoResult>>()
    val loading = MutableLiveData<Boolean>()

    fun insertMovie(movie: MovieResult?) = repository.insertMovie(movie)
    fun deleteMovie(movie: MovieResult?) = repository.deleteMovie(movie)
    fun getSingleMovie(movieId:Int?) : LiveData<MovieResult> = repository.getSingleMovie(movieId)
    fun getAllMovies(): LiveData<List<MovieResult>> = repository.getAllMovies()

    fun getMovieDetails(movieId:Int?){
        loading.value = true
        disposable.add(
            apiClient.getMovieDetails(movieId!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieDetailResponse>(){
                    override fun onSuccess(t: MovieDetailResponse) {
                        movieDetails.value = t
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.i("Detail View Model" , "Hata : "+e.message)
                    }

                })
        )
    }

    fun getMovieTrailers(movieId: Int?){
        loading.value = true
        disposable.add(
            apiClient.getMovieTrailers(movieId!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieVideoResponse>(){
                    override fun onSuccess(t: MovieVideoResponse) {
                        movieTrailers.value = t.results!!
                        loading.value = false
                    }
                    override fun onError(e: Throwable) {
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}