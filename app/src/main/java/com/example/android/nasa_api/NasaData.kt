package com.example.android.nasa_api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class NasaData (
    val date: String,
    val title: String,
    val url: String
)