package com.example.rick_and_morty.model

import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("info")
    var info: Info,
    @SerializedName("results")
    var result: List<Character>
)
