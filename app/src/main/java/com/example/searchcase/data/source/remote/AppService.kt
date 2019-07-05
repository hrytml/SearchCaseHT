package com.example.searchcase.data.source.remote

import com.example.searchcase.data.source.remote.model.SuggestionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET("/suggestion")
    fun getSuggestionsAsync(
        @Query("keyword") keyword: String,
        @Query("suggestionId") suggestionId: String
    ): Deferred<Response<List<SuggestionResponse>>>
}
