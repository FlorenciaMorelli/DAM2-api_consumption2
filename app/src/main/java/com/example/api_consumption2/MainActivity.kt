package com.example.api_consumption2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.api_consumption2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getListProducts()

    }

    fun getListProducts(){
        CoroutineScope(Dispatchers.IO).launch {
            val callApi = RetrofitClient.api.getProducts()
            runOnUiThread{
                if (callApi.isSuccessful){
                    val products = callApi.body()
                    Log.d("ProductApi", "Lista de productos: $products")
                } else {
                    Log.d("ProductApi", "No se obtuvo respuesta de la pegada")
                }
            }
        }
    }
}