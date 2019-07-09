package com.example.searchcase.ui.search

import com.example.searchcase.data.source.remote.SuggestionResponse

class SearchSuggestionViewState(private val response: List<SuggestionResponse>) {
    fun getSuggestionList(): List<SuggestionResponse> {
        return response
    }
}