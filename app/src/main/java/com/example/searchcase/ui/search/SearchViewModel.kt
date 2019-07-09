package com.example.searchcase.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchcase.data.repository.SearchRepository
import com.example.searchcase.data.source.remote.SuggestionResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    val suggestionResultLiveData: MutableLiveData<SearchSuggestionViewState> by lazy {
        MutableLiveData<SearchSuggestionViewState>()
    }

    val suggestionItemLiveData: MutableLiveData<SuggestionItemViewState> by lazy {
        MutableLiveData<SuggestionItemViewState>()
    }

    fun searchProduct(text: String) {
        CoroutineScope(Dispatchers.Default + Job()).launch {
            retrieveSearchResult(searchRepository.retrieveSuggestionResponse(text))
        }
    }

    private fun retrieveSearchResult(suggestionResponseList : List<SuggestionResponse>?) {
        if (suggestionResponseList != null) {
            suggestionResultLiveData.postValue(SearchSuggestionViewState(suggestionResponseList))
        }
    }

}