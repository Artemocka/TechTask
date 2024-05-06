package com.dracul.techtask.domain.repository

import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.models.Products

interface GetCategoriesRepo {
    suspend fun get(): Pair<List<String>, String?>
}