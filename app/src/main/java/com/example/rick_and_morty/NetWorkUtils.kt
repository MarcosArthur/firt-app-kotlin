package com.example.rick_and_morty

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetWorkUtils {

    companion object {
        fun getRetrofitInstance(path: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}