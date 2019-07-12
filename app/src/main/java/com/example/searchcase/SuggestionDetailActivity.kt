package com.example.searchcase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchcase.common.RecyclerViewEndlessScrollListener
import com.example.searchcase.data.source.remote.Product
import com.example.searchcase.data.source.remote.SearchRequest
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

    private lateinit var keyword: String


    private lateinit var suggestionDetailViewModel: SuggestionDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding: ActivitySuggestionDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_suggestion_detail)

        keyword = intent.getStringExtra(KEYWORD)

        Log.d("getBundle", keyword)

        suggestionDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(SuggestionDetailViewModel::class.java)


        suggestionDetailAdapter.suggestionDetailItemClickListener = this::openProductDetail

        suggestionDetailViewModel.suggestionDetailResultLiveData.observe(this, Observer{
                suggestionDetailStateView ->
            suggestionDetailAdapter.setSuggestionDetailList(suggestionDetailStateView.getSuggestionDetailList()?.products.orEmpty())
        })

        suggestionDetailViewModel.searchDetail(SearchRequest(keyword, 1))

        with(binding.RecyclerViewDetail) {
            adapter = suggestionDetailAdapter
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(this@SuggestionDetailActivity, 2)
        }

        binding.RecyclerViewDetail.addOnScrollListener(
            this@SuggestionDetailActivity::scrollListener
                .invoke(binding.RecyclerViewDetail.layoutManager as GridLayoutManager)
        )
    }

    private fun openProductDetail(contentId: String) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("contentID", contentId)
        startActivity(intent)
    }

    companion object {
        const val KEYWORD = "keyword"
    }

    private fun scrollListener(gridLayoutManager: GridLayoutManager): RecyclerView.OnScrollListener {
        return (object : RecyclerViewEndlessScrollListener(gridLayoutManager) {
            override fun onLoadNextPage(page: Int) {
                suggestionDetailViewModel.searchDetail(
                    SearchRequest(
                        keyword = keyword,
                        page = page
                    )
                )
            }
        })
    }
}

