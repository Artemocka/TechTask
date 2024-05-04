package com.dracul.techtask.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dracul.techtask.di.DaggerInjector
import com.dracul.techtask.di.components.main.DaggerMainComponent
import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.usecase.GetPageUseCase
import com.example.technotestvk.data.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject
    lateinit var getPageUseCase: GetPageUseCase

    val listProduct = MutableStateFlow<List<Product>>(emptyList())
    init {
        DaggerMainComponent.builder().dependencies(dependencies = DaggerInjector.appComponent).build().inject(this@MainViewModel)
        getProducts()
    }
    private fun getProducts(){

        viewModelScope.launch(Dispatchers.Main.immediate) {
            try {
                listProduct.value=getPageUseCase.execute(Page(index = 0)).products

            } catch (e:Exception){
                Log.e("",e.message.toString())
            }
        }
    }

}