package com.example.searchcase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchcase.databinding.ActivityMainBinding
import com.example.searchcase.ui.search.SearchViewModel
import com.example.searchcase.ui.search.SuggestionDetailViewModel
import com.example.searchcase.ui.search.adapter.SuggestionAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var suggestionAdapter: SuggestionAdapter


    private lateinit var searchViewModel: SearchViewModel

    private lateinit var suggestionDetailViewModel: SuggestionDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        //binding: layouttakileri basına binding ekleyerek kullanabiliyoruz bu tanımlamadan sonra ör:binding.buttonSearch
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        suggestionAdapter.setSuggestionList(emptyList())

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

            suggestionDetailViewModel.searchDetail(searchSuggestionStateView.getSuggestionList().first().id ?: "")

        })

        // searchViewModel.searchProduct("kitap")


        binding.buttonSearch.setOnClickListener {
            searchViewModel.searchProduct(binding.editTextSearch.text.toString())

        }

        suggestionDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(SuggestionDetailViewModel::class.java)

        suggestionDetailViewModel.suggestionDetailResultLiveData.observe(this, Observer{
                suggestionDetailStateView ->
            Log.d("TestDebug", "${suggestionDetailStateView.getSuggestionDetailList()?.products?.first()?.name}")
        })


    }
}
