package com.example.android.nasa_api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NasaDataRepository {
    var api: Api = NetworkService.retrofitService()
    private val nasaDataLiveData = MutableLiveData<Event<NasaData?>>()

    fun loadData(date: String= "") {
        nasaDataLiveData.postValue(Event.loading())

        api.getImageWithDate(date).enqueue(object : Callback<NasaData> {
            override fun onFailure(call: Call<NasaData?>, t: Throwable) {
                nasaDataLiveData.postValue(Event.error(Error(t)))
            }

            override fun onResponse(call: Call<NasaData?>, response: Response<NasaData>) {
                nasaDataLiveData.postValue(Event.success(response.body()))
            }
        })
    }
    fun nasaData(): LiveData<Event<NasaData?>> = nasaDataLiveData

}