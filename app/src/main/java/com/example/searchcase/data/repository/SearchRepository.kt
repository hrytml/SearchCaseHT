package com.example.searchcase.data.repository

import com.example.searchcase.data.source.SearchDataSource
import com.example.searchcase.data.source.remote.model.SuggestionResponse
import com.example.searchcase.data.source.remote.model.SearchSuggestionData
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchDataSource: SearchDataSource) {
    suspend fun retrieveSuggestionResponse(keyword: String): List<SuggestionResponse>? {
        return searchDataSource.retrieveSuggestionResponse(keyword).body()
    }
}