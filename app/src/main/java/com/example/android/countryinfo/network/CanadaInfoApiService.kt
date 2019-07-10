package com.example.android.countryinfo.network

import com.example.android.countryinfo.model.CanadaInfo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

// Using retrofit 2 to pull data from Network

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CanadaInfoApiService{
    @GET("facts.json")
    fun getCanadaInfo(): Call<CanadaInfo>
}

object CanadaApi{
    val retrofitService: CanadaInfoApiService by lazy{
        retrofit.create(CanadaInfoApiService::class.java)
    }
}