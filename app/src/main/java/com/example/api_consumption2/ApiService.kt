package com.example.api_consumption2

import com.example.api_consumption2.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products") // Defino el endpoint
    suspend fun getProducts():Response<List<Product>>

    // Obtener 1 producto
    @GET("products/1")
    fun getIndividualProduct():Response<Product>
}