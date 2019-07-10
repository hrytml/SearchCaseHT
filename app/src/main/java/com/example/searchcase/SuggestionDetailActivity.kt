package com.example.searchcase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.searchcase.data.source.remote.Product
import dagger.android.AndroidInjection
import com.example.searchcase.databinding.ActivitySuggestionDetailBinding
import com.example.searchcase.ui.search.SuggestionDetailViewModel
import com.example.searchcase.ui.search.adapter.SuggestionAdapter
import com.example.searchcase.ui.search.adapter.SuggestionDetailAdapter
import javax.inject.Inject

class SuggestionDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var suggestionDetailAdapter: SuggestionDetailAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var suggestionDetailViewModel: SuggestionDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding: ActivitySuggestionDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_suggestion_detail)

        val keyword = intent.getStringExtra(KEYWORD)

        Log.d("getBundle", keyword)

        suggestionDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(SuggestionDetailViewModel::class.java)

        suggestionDetailViewModel.suggestionDetailResultLiveData.observe(this, Observer{
                suggestionDetailStateView ->
            suggestionDetailAdapter.setSuggestionDetailList(suggestionDetailStateView.getSuggestionDetailList()?.products.orEmpty())
        })

        suggestionDetailViewModel.searchDetail(keyword)

        with(binding.RecyclerViewDetail) {
            adapter = suggestionDetailAdapter
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(this@SuggestionDetailActivity, 2)
        }

    }

    companion object {
        const val KEYWORD = "keyword"
    }

}

