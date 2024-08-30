package com.example.api_consumption2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_consumption2.adapter.ProductAdapter
import com.example.api_consumption2.databinding.ActivityMainBinding
import com.example.api_consumption2.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private lateinit var adapter: ProductAdapter
    private val products = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getListProducts()

        initRecycler()

    }

    private fun initRecycler(){
        adapter = ProductAdapter(products)
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = adapter
    }

    fun getListProducts(){
        CoroutineScope(Dispatchers.IO).launch {
            val callApi = RetrofitClient.api.getProducts()
            runOnUiThread{
                if (callApi.isSuccessful){
                    val productResponse = callApi.body()?: emptyList()
                    products.clear()
                    products.addAll(productResponse)
                    adapter.notifyDataSetChanged()
                } else {
                    Log.d("ProductApi", "No se obtuvo respuesta de la pegada")
                }
            }
        }
    }
}