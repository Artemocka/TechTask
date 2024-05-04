package com.dracul.techtask.di

import com.dracul.techtask.di.components.main.MainDependencies
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent: MainDependencies {

    @Component.Builder
    interface Builder{
        fun build():AppComponent
    }
}