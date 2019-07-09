package com.example.searchcase.data.source.remote

import com.google.gson.annotations.SerializedName

class SuggestionDetailResponse(
    @SerializedName("products")
    val products: List<Product>) {

}
