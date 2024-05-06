package com.dracul.techtask.domain.usecase

import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.models.Products
import com.dracul.techtask.domain.repository.GetCategoryProductsRepo
import com.dracul.techtask.domain.repository.GetPageRepo
import javax.inject.Inject


class GetCategoryProductsUseCase @Inject constructor(
    val repository: GetCategoryProductsRepo
) {
    suspend fun execute(category:String): Pair<List<Product>, String?> {
        return repository.get(category)
    }
}