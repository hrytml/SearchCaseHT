package com.example.searchcase.di.module

import com.example.searchcase.MainActivity
import com.example.searchcase.ProductDetailActivity
import com.example.searchcase.SuggestionDetailActivity
import com.example.searchcase.di.Scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun bindSuggestionDetailActivity(): SuggestionDetailActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun bindProductDetailActivity(): ProductDetailActivity
}


