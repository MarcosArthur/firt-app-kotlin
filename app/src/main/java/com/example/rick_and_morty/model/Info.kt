package com.example.rick_and_morty.model

data class Info(
    var count : Int,
    var pages : Int,
    var next  : String,
    var preve : String?
) {
    fun getNextPageId() : Int {
        return next.split("=").last().toInt()
    }

}
