package com.dracul.techtask.domain.repository

import com.dracul.techtask.domain.models.Product


interface GetCategoryProductsRepo {
    suspend fun get(category: String): Pair<List<Product>, String?>
}