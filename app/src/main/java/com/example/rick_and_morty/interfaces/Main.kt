package com.example.rick_and_morty.interfaces

import com.example.rick_and_morty.model.Characters
import com.example.rick_and_morty.model.Character
import retrofit2.Call

interface Main {
    interface View {
        fun loadCharacters(character: List<Character>)
        fun findCharacters()
        fun isLastItemOfList()
        fun showError(message: String)
        fun clear()
        fun hideProgressBar()
    }

    interface Presenter {
        fun getAllCharacters()
        fun findCharacters(name: String)
        fun getCharacters(person: Call<Characters>)
        fun getNextPageCharacters()
        fun getFirtPage()
    }

}