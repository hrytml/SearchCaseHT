package com.example.searchcase.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchcase.data.repository.SearchRepository
import com.example.searchcase.data.source.remote.model.SuggestionResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    val suggestionResultLiveData: MutableLiveData<SearchSuggestionViewState> by lazy {
        MutableLiveData<SearchSuggestionViewState>()
    }

    fun searchProduct(text: String) {
        CoroutineScope(Dispatchers.Default + Job()).launch {
            retrieveSearchResult(searchRepository.retrieveSuggestionResponse("kozmetik"))
        }
    }


    private fun retrieveSearchResult(suggestionResponseList : List<SuggestionResponse>?) {
        if (suggestionResponseList != null) {
            suggestionResultLiveData.postValue(SearchSuggestionViewState(suggestionResponseList))
        }
    }
}