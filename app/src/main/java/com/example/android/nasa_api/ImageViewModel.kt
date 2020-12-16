package com.example.android.nasa_api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class ImageViewModel : ViewModel() {
    private val repository: NasaDataRepository = NasaDataRepository()
    fun loadData(date: String= ""){
        repository.loadData(date)
    }
    fun nasaData(): LiveData<Event<NasaData?>> = repository.nasaData()
}