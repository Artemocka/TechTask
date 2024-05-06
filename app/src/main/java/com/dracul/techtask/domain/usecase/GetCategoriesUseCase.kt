package com.dracul.techtask.domain.usecase

import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.repository.GetPageRepo
import com.dracul.techtask.domain.models.Products
import com.dracul.techtask.domain.repository.GetCategoriesRepo
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    val repository: GetCategoriesRepo
) {
    suspend fun execute(page: Page): Pair<List<String>, String?> {
        return repository.get(page)
    }
}