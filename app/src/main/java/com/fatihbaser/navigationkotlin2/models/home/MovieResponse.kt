package com.fatihbaser.navigationkotlin2.models.home

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    var results: List<MovieResult>
)