package com.example.searchcase.data.source.remote

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

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

    @GET("product/v2/{contentId}")
    fun getProductDetailResultAsync(
        @Path("contentId") contentId: String
    ): Deferred<Response<ProductDetailResponse>>

}
