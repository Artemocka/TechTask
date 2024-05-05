package com.dracul.techtask.domain.usecase

import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.repository.GetPageRepo
import com.dracul.techtask.domain.models.Products
import javax.inject.Inject

class GetPageUseCase @Inject constructor(
    val repository: GetPageRepo
) {
    suspend fun execute(page: Page): Pair<List<Product>, String?> {
        return repository.get(page)
    }
}