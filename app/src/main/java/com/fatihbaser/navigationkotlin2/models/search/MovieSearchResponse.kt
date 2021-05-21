package com.fatihbaser.navigationkotlin2.models.search

import com.google.gson.annotations.SerializedName


data class MovieSearchResponse(
    @SerializedName("results")
    var results: List<MovieSearchResult>
)