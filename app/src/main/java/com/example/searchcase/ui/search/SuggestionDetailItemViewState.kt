package com.example.searchcase.ui.search

import android.view.View
import com.example.searchcase.data.source.remote.Product

class SuggestionDetailItemViewState(private val product: Product) {
    fun getCategoryName(): String? {
        return product.categoryName
    }

    fun getSalePrice(): String{
        return product.salePrice.toString() + "TL"

    }

    fun getMarketPriceVisibility(): Int {
        return if(product.marketPrice <= product.salePrice) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    fun getMarketPrice(): String {
        return product.marketPrice.toString() + "TL"
    }

    /*fun getCategoryName(): String? {
        return product.categoryName
    }*/

    fun getBrandName(): String? {
        return product.brandName
    }

    fun getColorName(): String? {
        return product.colorName
    }

    fun getImageUrl(): String? {
        return product.imageUrl
    }

    fun getDiscountedPrice(): String{
        return product.discountedPrice.toString() + "TL"
    }



    // getMarketPriceVisibility
}
