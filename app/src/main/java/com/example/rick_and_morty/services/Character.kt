package com.example.rick_and_morty.services

import com.example.rick_and_morty.model.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Character {

    @GET("character")
    fun getAll(): Call<Characters>

    @GET("character")
    fun getNextPage(@Query("page") page : Int) : Call<Characters>

    @GET("character/{id}")
    fun findById(@Path("id") id: Int) : Call<Character>

    @GET("character")
    fun findByName(@Query("name") name : String) : Call<Characters>
}