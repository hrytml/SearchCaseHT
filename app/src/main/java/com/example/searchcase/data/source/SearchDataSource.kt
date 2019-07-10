package com.example.searchcase.data.source

import com.example.searchcase.data.source.remote.*
import retrofit2.Response
import javax.inject.Inject

class SearchDataSource @Inject constructor(private val appService: AppService){
    suspend fun retrieveSuggestionResponse(keyword: String): Response<List<SuggestionResponse>> {
        return appService.getSuggestionsAsync(keyword, "1").await()
    }

    suspend fun retrieveSuggestionDetailResponse(keyword: SearchRequest): Response<SuggestionDetailResponse> {
        return appService.fetchSuggestionDetailAsync(keyword).await()
    }

    suspend fun retrieveProductDetailResponse(contentId: String): Response<ProductDetailResponse> {
        return appService.getProductDetailResultAsync(contentId).await()
    }
}