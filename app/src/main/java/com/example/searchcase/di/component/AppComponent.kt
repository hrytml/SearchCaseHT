package com.example.searchcase.di.component

import android.app.Application
import com.example.searchcase.SearchCaseApplication
import com.example.searchcase.di.module.ActivityBuilderModule
import com.example.searchcase.di.module.NetworkModule
import com.example.searchcase.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<SearchCaseApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<SearchCaseApplication>() {
        @BindsInstance
        abstract fun app(application: Application): Builder
    }

}