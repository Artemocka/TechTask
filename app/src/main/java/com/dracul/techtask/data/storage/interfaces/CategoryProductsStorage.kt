package com.dracul.techtask.data.storage.interfaces

import com.dracul.techtask.domain.models.Product

interface CategoryProductsStorage {
    suspend fun get(category:String): Pair<List<Product>, String?>
}