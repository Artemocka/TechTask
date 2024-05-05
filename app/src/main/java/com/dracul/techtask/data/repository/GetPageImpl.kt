package com.dracul.techtask.data.repository

import com.dracul.techtask.data.storage.interfaces.PageStorage
import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.repository.GetPageRepo
import com.dracul.techtask.domain.models.Products
import javax.inject.Inject

class GetPageImpl @Inject constructor(
    val pageStorage: PageStorage
) : GetPageRepo {

    override suspend fun get(page: Page): Pair<List<Product>, String?> {
        return pageStorage.get(page)
    }
}