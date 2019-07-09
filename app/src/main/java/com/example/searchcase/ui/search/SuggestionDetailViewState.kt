package com.example.searchcase.ui.search

import com.example.searchcase.data.source.remote.SuggestionDetailResponse

class SuggestionDetailViewState(private val response: SuggestionDetailResponse?) {
    fun getSuggestionDetailList(): SuggestionDetailResponse? {
        return response
    }
}