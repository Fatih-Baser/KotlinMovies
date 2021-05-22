package com.fatihbaser.navigationkotlin2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fatihbaser.navigationkotlin2.models.search.MovieSearchResponse
import com.fatihbaser.navigationkotlin2.models.search.MovieSearchResult
import com.fatihbaser.navigationkotlin2.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val apiClient = ApiClient()
    private val disposable = CompositeDisposable()

    val searchMovies = MutableLiveData<List<MovieSearchResult>>()

    fun getSearchMovies(search: String) {
        disposable.add(
            apiClient.getSearchMovies(search)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieSearchResponse>() {
                    override fun onSuccess(t: MovieSearchResponse) {
                        searchMovies.value = t.results!!
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