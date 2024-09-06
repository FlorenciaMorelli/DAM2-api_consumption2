package com.example.api_consumption2

import android.util.Log
import com.example.api_consumption2.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsRepository {
    interface ProductsResults{
        fun onSuccess(products: List<Product>)
        fun onFailure()
    }

    fun fetchProducts(productResults: ProductsResults){
        //  Pegada a la api con try catch
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = RetrofitClient.api.getProducts()
                if (call.isSuccessful){
                    productResults.onSuccess(call.body() ?: emptyList())
                } else {
                    productResults.onFailure()
                }
            } catch (e: Exception){
                Log.d("ProductApi", "Error: ${e.message}")
            }
        }
    }
}