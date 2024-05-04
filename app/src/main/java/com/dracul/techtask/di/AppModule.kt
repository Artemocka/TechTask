package com.dracul.techtask.di


import com.dracul.techtask.data.api.ProductApi
import com.dracul.techtask.data.repository.GetPageImpl
import com.dracul.techtask.data.storage.impl.NetworkPageStorage
import com.dracul.techtask.data.storage.interfaces.PageStorage
import com.dracul.techtask.domain.repository.GetPageRepo
import com.dracul.techtask.domain.usecase.GetPageUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
object AppModule {

    @Provides
    fun provideProductApi(): ProductApi {
        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
        return retrofit
    }

    @Provides
    fun provideNetworkStorage(api: ProductApi): PageStorage {
        return NetworkPageStorage(api)
    }

    @Provides
    fun provideGetPageRepo(storage: PageStorage): GetPageRepo {
        return GetPageImpl(storage)
    }
    @Provides
    fun provideGetPageUseCase(repo: GetPageRepo): GetPageUseCase {
        return GetPageUseCase(repo)
    }




}