package com.fatihbaser.navigationkotlin2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fatihbaser.navigationkotlin2.models.home.MovieResult
import com.fatihbaser.navigationkotlin2.service.ApiClient
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val apiclient=ApiClient()
    private val disposable=CompositeDisposable()


    val popularMovie = MutableLiveData<List<MovieResult>>()
    val upcomingMovie = MutableLiveData<List<MovieResult>>()
    val loadingMovies = MutableLiveData<Boolean>()



    fun getPopularMovies(){

    }
}