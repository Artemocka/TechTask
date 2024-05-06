package com.dracul.techtask.data.repository

import com.dracul.techtask.data.storage.interfaces.CategoriesStorage
import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.repository.GetCategoriesRepo
import javax.inject.Inject

class GetCategoriesImpl @Inject constructor(
    val categoriesStorage: CategoriesStorage
) : GetCategoriesRepo {

    override suspend fun get(): Pair<List<String>, String?> {
        return categoriesStorage.get()
    }
}