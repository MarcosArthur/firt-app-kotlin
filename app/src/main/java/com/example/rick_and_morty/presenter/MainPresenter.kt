package com.example.rick_and_morty.presenter

import android.util.Log
import com.example.rick_and_morty.NetWorkUtils
import com.example.rick_and_morty.exceptions.NotFoundCharacter
import com.example.rick_and_morty.interfaces.Main
import com.example.rick_and_morty.model.Characters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainPresenter() : Main.Presenter {

    lateinit var view: Main.View
    var nextPage: Int = 0
    val BASE_URL = "https://rickandmortyapi.com/api/"
    var net = NetWorkUtils.getRetrofitInstance(BASE_URL)
    var person = net.create(com.example.rick_and_morty.services.Character::class.java)

    fun init() {
        getAllCharacters()
        view.hideProgressBar()
    }

    override fun getNextPageCharacters() {

        if (nextPage != 0) {
            var newPersons = person.getNextPage(nextPage)
            getCharacters(newPersons)
        }

    }

    override fun getAllCharacters() {
        val persons = person.getAll()
        getCharacters(persons)
    }

    override fun getCharacters(person: Call<Characters>) {
        person.enqueue(object : Callback<Characters> {
            override fun onResponse(
                call: Call<Characters>, response: Response<Characters>
            ) {
                try {
                    var results = response.body()

                    if (response.isSuccessful) {
                        results?.info?.let { nextPage = it.getNextPageId() }
                        results?.result?.let { view.loadCharacters(it) }
                    } else {
                        throw NotFoundCharacter(response.errorBody()?.string())
                    }

                } catch (e: Exception) {
                    e.message?.let { view.showError(it) }
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                t.message?.let { view.showError(it) };
            }

        })
    }

    override fun findCharacters(name: String) {
        val results = person.findByName(name)
        view.clear()
        getCharacters(results)
    }

    override fun getFirtPage() {
        nextPage = 1
        getAllCharacters()
    }


}
