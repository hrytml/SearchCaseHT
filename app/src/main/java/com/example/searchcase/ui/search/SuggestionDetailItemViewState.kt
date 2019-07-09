package com.example.searchcase.ui.search

import com.example.searchcase.data.source.remote.SuggestionDetailResponse

class SuggestionDetailItemViewState(private val suggestionDetailItem: SuggestionDetailResponse) {
    fun getSuggestionItemName(): String? {
        return suggestionDetailItem.toString()
    }
}
