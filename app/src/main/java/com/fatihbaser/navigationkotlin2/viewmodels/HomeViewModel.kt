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

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val apiClient = ApiClient()
    private val disposable=CompositeDisposable()


    val popularMovie = MutableLiveData<List<MovieResult>>()
    val upcomingMovie = MutableLiveData<List<MovieResult>>()
    val loadingMovies = MutableLiveData<Boolean>()



    fun getPopularMovies(){
        loadingMovies.value = true
        disposable.add(
            apiClient.getPopularMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                    override fun onSuccess(t: MovieResponse) {
                        popularMovie.value = t.results
                        loadingMovies.value = false
                        Log.i("HOME FRAGMENT : ", "POPULAR WORKING")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("HOME FRAGMENT : ", "UPCOMING ERROR"+e.message)
                    }

                })
        )
    }

    fun getUpcomingMovies(){
        loadingMovies.value = true
        disposable.add(
            apiClient.getUpcomingMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                    override fun onSuccess(t: MovieResponse) {
                        upcomingMovie.value = t.results
                        loadingMovies.value = false
                        Log.i("HOME FRAGMENT : ", "UPCOMING WORKING")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("HOME FRAGMENT : ", "UPCOMING ERROR"+e.message)
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
