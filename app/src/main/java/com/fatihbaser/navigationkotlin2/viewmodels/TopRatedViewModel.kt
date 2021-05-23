package com.fatihbaser.navigationkotlin2.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fatihbaser.navigationkotlin2.models.home.MovieResponse
import com.fatihbaser.navigationkotlin2.models.home.MovieResult
import com.fatihbaser.navigationkotlin2.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TopRatedViewModel(application: Application) : AndroidViewModel(application) {

    private val apiClient = ApiClient()
    private val disposable = CompositeDisposable()

    val topRatedMovie = MutableLiveData<List<MovieResult>>()
    val loadingTopRatedMovie = MutableLiveData<Boolean>()

    fun getTopRatedMovies(){
        loadingTopRatedMovie.value = true
        disposable.add(
            apiClient.getTopRatedMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                    override fun onSuccess(t: MovieResponse) {
                        topRatedMovie.value = t.results
                        loadingTopRatedMovie.value = false
                        Log.i("POPULAR VIEW MODEL", "ÇALIŞTI")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("POPULAR VIEW MODEL", " "+e.message)
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}