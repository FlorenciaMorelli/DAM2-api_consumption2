package com.example.api_consumption2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_consumption2.adapter.ProductAdapter
import com.example.api_consumption2.databinding.ActivityMainBinding
import com.example.api_consumption2.model.Product

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val productsRepository = ProductsRepository()
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getListProducts()

        initRecycler()

    }

    private fun initRecycler(){
        binding.rvProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun getListProducts(){
        productsRepository.fetchProducts(
            //  Implementar funciones de productsRepository
                //  Objeto anónimo
            object : ProductsRepository.ProductsResults{
                override fun onSuccess(products: List<Product>) {
                    runOnUiThread{
                        adapter = ProductAdapter(products)
                        binding.rvProducts.adapter = adapter
                        adapter.notifyDataSetChanged()
                        Log.d("ProductApi", "List products: $products")
                    }
                }

                override fun onFailure() {
                    Toast.makeText(this@MainActivity, "Error al obtener la información, intente más tarde", Toast.LENGTH_LONG).show()
                    Log.d("ProductApi", "No se pudo obtener la info")
                }

            }
        )
    }
}