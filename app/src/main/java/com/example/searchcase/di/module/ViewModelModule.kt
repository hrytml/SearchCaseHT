package com.example.searchcase.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.searchcase.di.ViewModelFactory
import com.example.searchcase.ui.search.SearchViewModel
import com.example.searchcase.ui.search.SuggestionDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SuggestionDetailViewModel::class)
    abstract fun bindSuggestionDetailViewModel(suggestionDetailViewModel: SuggestionDetailViewModel): ViewModel
}