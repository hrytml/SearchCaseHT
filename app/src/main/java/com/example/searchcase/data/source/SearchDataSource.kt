package com.example.searchcase.data.source

import com.example.searchcase.data.source.remote.AppService
import com.example.searchcase.data.source.remote.model.SuggestionResponse
import com.example.searchcase.data.source.remote.model.SearchSuggestionData
import retrofit2.Response
import javax.inject.Inject

class SearchDataSource @Inject constructor(private val appService: AppService){
    suspend fun retrieveSuggestionResponse(keyword: String): Response<List<SuggestionResponse>> {
        return appService.getSuggestionsAsync(keyword, "1").await()
    }
}