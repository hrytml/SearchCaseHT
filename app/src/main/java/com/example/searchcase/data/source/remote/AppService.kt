package com.example.searchcase.data.source.remote

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AppService {

    @GET("suggestion")
    fun getSuggestionsAsync(
        @Query("keyword") keyword: String,
        @Query("suggestionId") suggestionId: String
    ): Deferred<Response<List<SuggestionResponse>>>

    @POST("searchV2")
    fun fetchSuggestionDetailAsync(
        @Body searchRequest: SearchRequest
    ): Deferred<Response<SuggestionDetailResponse>>

}
