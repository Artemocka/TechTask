package com.dracul.techtask.di.components.main

import com.dracul.techtask.domain.usecase.GetPageUseCase
import com.dracul.techtask.viewmodels.MainViewModel
import dagger.Component
import dagger.Module

@Component(modules = [MainModule::class], dependencies = [MainDependencies::class])
interface MainComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: MainDependencies): Builder
        fun build(): MainComponent
    }

    fun inject(target: MainViewModel)
}

@Module
class MainModule

interface MainDependencies {
    fun getPageUseCase(): GetPageUseCase
}
