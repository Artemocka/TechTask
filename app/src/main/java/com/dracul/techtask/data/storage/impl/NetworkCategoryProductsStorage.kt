package com.dracul.techtask.data.storage.impl

import com.dracul.techtask.data.api.ProductApi
import com.dracul.techtask.data.storage.interfaces.CategoryProductsStorage
import com.dracul.techtask.domain.models.Product
import kotlinx.coroutines.CancellationException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkCategoryProductsStorage @Inject constructor(
    val api: ProductApi,
) : CategoryProductsStorage {
    override suspend fun get(category:String): Pair<List<Product>, String?> {

        var products: List<Product> = emptyList()
        var errorMessage:String? = null

        try {
            products= api.getCategory(category).products
        } catch (e: UnknownHostException) {
            errorMessage ="No internet connections!"
        } catch (e: SocketTimeoutException) {
            errorMessage="Bad internet connections"
        }catch (_: CancellationException) {
        }
        catch (e: Exception) {
            errorMessage="Unknown error"
        }

        return Pair(products, errorMessage)
    }
}