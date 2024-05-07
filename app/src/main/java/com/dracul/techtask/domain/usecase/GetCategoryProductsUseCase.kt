package com.dracul.techtask.domain.usecase

import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.repository.GetCategoryProductsRepo
import javax.inject.Inject


class GetCategoryProductsUseCase @Inject constructor(
    val repository: GetCategoryProductsRepo
) {
    suspend fun execute(category:String): Pair<List<Product>, String?> {
        return repository.get(category)
    }
}