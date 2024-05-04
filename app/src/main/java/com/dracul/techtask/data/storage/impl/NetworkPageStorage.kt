package com.dracul.techtask.data.storage.impl

import com.dracul.techtask.data.api.ProductApi
import com.dracul.techtask.data.storage.interfaces.PageStorage
import com.dracul.techtask.domain.models.Page
import com.example.technotestvk.data.Products
import javax.inject.Inject

class NetworkPageStorage @Inject constructor(
    val api: ProductApi,
) : PageStorage {
    override suspend fun get(page: Page):Products{
        return api.getPage(page.getSkip(), page.getLimit())
    }
}