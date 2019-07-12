package com.example.searchcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.searchcase.databinding.ActivityProductDetailBinding
import com.example.searchcase.ui.search.ProductDetailViewModel
import com.example.searchcase.ui.search.SuggestionDetailViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


class ProductDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var productDetailModel: ProductDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val contentID =  intent.getStringExtra("contentID")

        val binding: ActivityProductDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        productDetailModel = ViewModelProviders.of(this, viewModelFactory).get(ProductDetailViewModel::class.java)


        productDetailModel.searchProduct(contentID)

        productDetailModel.productDetailResultLiveData.observe(this, Observer { productDetailStateView ->
            binding.productDetailViewState = productDetailStateView
            binding.executePendingBindings()
        }
        )

    }
}
