package com.example.searchcase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.searchcase.databinding.ActivityMainBinding
import com.example.searchcase.ui.search.SearchViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        //binding: layouttakileri basına binding ekleyerek kullanabiliyoruz bu tanımlamadan sonra ör:binding.buttonSearch
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        searchViewModel.suggestionResultLiveData.observe(this, Observer {
            Log.d("TestDebug", "${it.getSuggestionList()}")

            binding.searchSuggestionViewState = it
            binding.executePendingBindings()
        })

        searchViewModel.searchProduct("Ugur")
    }
}
