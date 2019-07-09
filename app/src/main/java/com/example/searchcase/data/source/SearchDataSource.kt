package com.example.searchcase.data.source

import com.example.searchcase.data.source.remote.AppService
import com.example.searchcase.data.source.remote.SearchRequest
import com.example.searchcase.data.source.remote.SuggestionDetailResponse
import com.example.searchcase.data.source.remote.SuggestionResponse
import retrofit2.Response
import javax.inject.Inject

class SearchDataSource @Inject constructor(private val appService: AppService){
    suspend fun retrieveSuggestionResponse(keyword: String): Response<List<SuggestionResponse>> {
        return appService.getSuggestionsAsync(keyword, "1").await()
    }

    suspend fun retrieveSuggestionDetailResponse(keyword: SearchRequest): Response<SuggestionDetailResponse> {
        return appService.fetchSuggestionDetailAsync(keyword).await()
    }
}