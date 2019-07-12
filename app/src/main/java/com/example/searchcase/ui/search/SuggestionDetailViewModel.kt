package com.example.searchcase.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchcase.data.repository.SearchRepository
import com.example.searchcase.data.source.remote.Product
import com.example.searchcase.data.source.remote.SearchRequest
import com.example.searchcase.data.source.remote.SuggestionDetailResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SuggestionDetailViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    val suggestionDetailResultLiveData: MutableLiveData<SuggestionDetailViewState> by lazy {
        MutableLiveData<SuggestionDetailViewState>()
    }

    val suggestionDetailItemLiveData: MutableLiveData<SuggestionDetailItemViewState> by lazy {
        MutableLiveData<SuggestionDetailItemViewState>()
    }

    fun searchDetail(searchRequest: SearchRequest) {
        CoroutineScope(Dispatchers.Default + Job()).launch {
            retrieveSearchDetailResult(searchRepository.retrieveSuggestionDetailResponse(searchRequest))
            Log.d("Detail", "ayakkabı")
        }
    }

    private fun retrieveSearchDetailResult(retrieveSuggestionDetailResponse: SuggestionDetailResponse?) {
        if (retrieveSuggestionDetailResponse != null){
            suggestionDetailResultLiveData.postValue(SuggestionDetailViewState(retrieveSuggestionDetailResponse))
        }
    }

}