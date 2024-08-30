package com.example.api_consumption2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

    //  Creo el client para hacer pegadas a determinada URL por medio de Retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val api = retrofit.create(ApiService::class.java)
}