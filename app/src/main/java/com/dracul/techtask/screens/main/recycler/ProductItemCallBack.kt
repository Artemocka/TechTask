package com.dracul.techtask.screens.main.recycler

import androidx.recyclerview.widget.DiffUtil
import com.dracul.techtask.domain.models.Product

class ProductItemCallBack : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}