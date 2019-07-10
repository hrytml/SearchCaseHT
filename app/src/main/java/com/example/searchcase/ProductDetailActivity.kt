package com.example.searchcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.searchcase.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import java.util.*

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)


        val binding: ActivityProductDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        productDetailModel.productDetailResultLiveData.observe(this, Observer{
                productDetailStateView ->
            binding.productDetailViewState = productDetailViewState
            binding.executePendingBindings()


        }
    })


