package com.example.android4a.presentation.model

class RestFinalFantasyResponse {
    private val results: List<FinalFantasy>? = null
    fun getResults(): MutableList<FinalFantasy> {
        return (results as MutableList<FinalFantasy>?)!!
    }

}