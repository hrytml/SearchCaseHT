package com.example.searchcase.ui.search

import com.example.searchcase.data.source.remote.ProductDetailResponse

class ProductDetailViewState(private val response: ProductDetailResponse?) {
    fun getProductDetailList(): ProductDetailResponse? {
        return response
    }
}



