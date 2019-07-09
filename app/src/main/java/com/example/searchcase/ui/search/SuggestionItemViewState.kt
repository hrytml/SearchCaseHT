package com.example.searchcase.ui.search

import com.example.searchcase.data.source.remote.SuggestionResponse

class SuggestionItemViewState(private val suggestionItem: SuggestionResponse) {
    fun getSuggestionName(): String? {
        return suggestionItem.text
    }

    fun getCategoryName(): String? {
        return suggestionItem.eventSuggestionName
    }
}
