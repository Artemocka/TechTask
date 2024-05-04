package com.dracul.techtask.domain.usecase

import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.repository.GetPageRepo
import com.example.technotestvk.data.Products
import javax.inject.Inject

class GetPageUseCase @Inject constructor(
    val repository: GetPageRepo
) {
    suspend fun execute(page: Page): Products {
        return repository.get(page)
    }
}