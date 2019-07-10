package com.example.searchcase.data.repository

import android.graphics.pdf.PdfDocument
import com.example.searchcase.data.source.SearchDataSource
import com.example.searchcase.data.source.remote.*
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchDataSource: SearchDataSource) {
    suspend fun retrieveSuggestionResponse(keyword: String): List<SuggestionResponse>? {
        return searchDataSource.retrieveSuggestionResponse(keyword).body()
    }

    suspend fun retrieveSuggestionDetailResponse(keyword: SearchRequest): SuggestionDetailResponse? {
        return searchDataSource.retrieveSuggestionDetailResponse(keyword).body()
    }


    suspend fun retrieveProductDetailResponse(keyword: String): ProductDetailResponse? {
        return searchDataSource.retrieveProductDetailResponse(keyword).body()
    }
}
