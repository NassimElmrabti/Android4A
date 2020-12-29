package com.example.android4a.presentation.main

import androidx.lifecycle.MutableLiveData
import com.example.android4a.data.finalFantasyApi
import com.example.android4a.presentation.model.FinalFantasy
import com.example.android4a.presentation.model.RestFinalFantasyResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class listViewModel {

    fun makeApiCall(){

        val apiLiveData: MutableLiveData<ApiStatus> = MutableLiveData()

        var gsonBuilder = GsonBuilder()
            .setLenient()
            .create()

        var retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/kalash94/Android4A/master/app/src/main/java/com/example/android4a/presentation/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            val FFApi : finalFantasyApi = retrofit.create(finalFantasyApi::class.java)

        var call: Call<RestFinalFantasyResponse> = FFApi.getFinalFantasyResponse()

        call.enqueue(object : Callback<RestFinalFantasyResponse>{
            override fun onResponse(call: Call<RestFinalFantasyResponse>?, response: Response<RestFinalFantasyResponse>?) {
                if (response != null) {
                    if(response.isSuccessful) {
                        val FFList: List<FinalFantasy> = response!!.body().getResults()
                        apiLiveData.value = ApiStatusSuccess(FFList)
                    }else{
                        apiLiveData.value = ApiStatusError
                    }
                }
            }

            override fun onFailure(call: Call<RestFinalFantasyResponse>?, t: Throwable?) {
                    apiLiveData.value = ApiStatusError
            }
        })
    }


    }