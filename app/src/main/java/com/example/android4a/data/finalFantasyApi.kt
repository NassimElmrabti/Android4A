package com.example.android4a.data

import com.example.android4a.presentation.model.RestFinalFantasyResponse
import retrofit2.Call
import retrofit2.http.GET

interface finalFantasyApi {

    @GET("FinalFantasyApi.json")
    open fun getFinalFantasyResponse(): Call<RestFinalFantasyResponse>
}