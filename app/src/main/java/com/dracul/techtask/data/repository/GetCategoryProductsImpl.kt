package com.dracul.techtask.data.repository

import com.dracul.techtask.data.storage.interfaces.CategoryProductsStorage
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.repository.GetCategoryProductsRepo
import javax.inject.Inject

class GetCategoryProductsImpl @Inject constructor(
    val categoriesStorage: CategoryProductsStorage
) : GetCategoryProductsRepo {

    override suspend fun get(category:String): Pair<List<Product>, String?> {
        return categoriesStorage.get(category)
    }
}