package com.example.searchcase.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchcase.data.repository.SearchRepository
import com.example.searchcase.data.source.remote.ProductDetailResponse
import com.example.searchcase.data.source.remote.SearchRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    val productDetailResultLiveData: MutableLiveData<ProductDetailViewState> by lazy {
        MutableLiveData<ProductDetailViewState>()
    }

    fun searchProduct(contentId: String){
        CoroutineScope(Dispatchers.Default + Job()).launch {
            retrieveProductDetailResult(searchRepository.retrieveProductDetailResponse(contentId))
        }
    }

    private fun retrieveProductDetailResult(retrieveProductDetailResponse: ProductDetailResponse?) {
        if (retrieveProductDetailResponse != null){
            productDetailResultLiveData.postValue(ProductDetailViewState(retrieveProductDetailResponse.product))
        }
    }

}