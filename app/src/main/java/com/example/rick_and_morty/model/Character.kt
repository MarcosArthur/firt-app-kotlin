package com.example.rick_and_morty.model

data class Character(
    var id: Int,
    var name: String,
    var image: String,
    var status: String,
    var species: String
) {
    fun situation() : String {
        return status.plus(" ").plus(species)
    }

    fun identifier() : String {
        return id.toString()
    }


}
