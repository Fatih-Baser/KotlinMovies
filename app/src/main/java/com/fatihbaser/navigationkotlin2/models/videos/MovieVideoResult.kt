package com.fatihbaser.navigationkotlin2.models.videos

import com.google.gson.annotations.SerializedName

data class MovieVideoResult(
    @SerializedName("id")
    var id: String,

    @SerializedName("key")
    var key: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("site")
    var site: String
)
