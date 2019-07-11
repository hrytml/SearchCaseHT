package com.example.searchcase.data.source.remote

class ProductResponse(
    val brandName: String?,
    val name: String?,
    val images: List<String>?,
    val salePrice: Double?,
    val discountedPrice: Double?,
    val contentId :String?,
    val campaignId :String?,
    val merchantId :String?
)
