package com.example.rick_and_morty.exceptions

import org.json.JSONObject

class NotFoundCharacter(response: String?) : Exception() {

    override lateinit var message: String

    var response: String? = response

    init {
        this.message = this.processError()
    }

    private fun processError(): String {
        val errorJsonObject = JSONObject(response)
        return errorJsonObject.getString("error")
    }
    
}