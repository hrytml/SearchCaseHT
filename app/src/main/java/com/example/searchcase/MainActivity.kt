package com.example.searchcase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchcase.databinding.ActivityMainBinding
import com.example.searchcase.ui.search.SearchViewModel
import com.example.searchcase.ui.search.adapter.SuggestionAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var suggestionAdapter: SuggestionAdapter


    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        //binding: layouttakileri basına binding ekleyerek kullanabiliyoruz bu tanımlamadan sonra ör:binding.buttonSearch
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        suggestionAdapter.setSuggestionList(emptyList())

        suggestionAdapter.suggestionItemClickListener = this::suggestionItemClicked

        with(binding.RecyclerViewSearch) {
            adapter = suggestionAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        searchViewModel.suggestionResultLiveData.observe(this, Observer {
            searchSuggestionStateView ->

            // Log.d("TestDebug", "${searchSuggestionStateView.getSuggestionList()[0].text}")

            // binding.textViewFirstResult.text = searchSuggestionStateView.getSuggestionList()[0].text

            suggestionAdapter.setSuggestionList(searchSuggestionStateView.getSuggestionList())

            binding.searchSuggestionViewState = searchSuggestionStateView
            binding.executePendingBindings()

        })

        // searchViewModel.searchProduct("kitap")


        binding.buttonSearch.setOnClickListener {
            searchViewModel.searchProduct(binding.editTextSearch.text.toString())

        }

    }

    fun suggestionItemClicked(keyword: String) {
        Log.d("recyc" , "$keyword")

        val intent = Intent(this, SuggestionDetailActivity::class.java)
        intent.putExtra(SuggestionDetailActivity.KEYWORD, keyword)

        startActivity(intent)
    }
}
