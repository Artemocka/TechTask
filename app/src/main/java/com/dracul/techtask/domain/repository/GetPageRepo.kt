package com.dracul.techtask.domain.repository

import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.models.Products

interface GetPageRepo {
    suspend fun get(page: Page): Pair<List<Product>, String?>
}