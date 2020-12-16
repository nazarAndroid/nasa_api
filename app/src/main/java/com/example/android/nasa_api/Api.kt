package com.example.android.nasa_api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("apod?api_key=asdOzM1d6YY0vx8rcK67eRIStlkSLLJlQfQucYKp")
    fun getImageWithDate(@Query("date") date: String?): Call<NasaData>
}