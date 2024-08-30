package com.example.api_consumption2.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.api_consumption2.databinding.ItemProductBinding
import com.example.api_consumption2.model.Product
import com.squareup.picasso.Picasso

class ProductViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)

    fun bind(product: Product){
        binding.tvProduct.text = product.title
        Picasso.with(itemView.context).load(product.productImage).into(binding.ivProduct)
    }
}
