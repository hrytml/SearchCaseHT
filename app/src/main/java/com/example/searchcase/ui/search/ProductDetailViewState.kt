package com.example.searchcase.ui.search

import com.example.searchcase.data.source.remote.ProductDetailResponse
import com.example.searchcase.data.source.remote.ProductResponse

class ProductDetailViewState(private val product: ProductResponse?) {
    fun getBrandName(): String {
        return product?.brandName.toString()
    }

    fun getName(): String{
        return product?.name.toString()
    }

    fun getImage(): String{
        return product?.images?.first().toString()
    }

    fun getSalePrice(): String{
        return product?.salePrice.toString()
    }

    fun getDiscountedPrice(): String{
        return product?.discountedPrice.toString()
    }

}



