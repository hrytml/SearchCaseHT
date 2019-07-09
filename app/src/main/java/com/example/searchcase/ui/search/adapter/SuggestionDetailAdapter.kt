package com.example.searchcase.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.searchcase.R
import com.example.searchcase.data.source.remote.SuggestionDetailResponse
import com.example.searchcase.databinding.ItemSuggestionBinding
import com.example.searchcase.ui.search.SuggestionDetailItemViewState
import com.example.searchcase.ui.search.SuggestionItemViewState
import javax.inject.Inject

class SuggestionDetailAdapter @Inject constructor():
    RecyclerView.Adapter<SuggestionDetailAdapter.ViewHolder>() {

    private val suggestionDetailList = mutableListOf<SuggestionDetailResponse>()

    fun setSuggestionDetailList(suggestionDetails: List<SuggestionDetailResponse>) {
        val beforeSize = suggestionDetailList.size

        suggestionDetailList.addAll(suggestionDetails)
        notifyItemRangeInserted(beforeSize,suggestionDetails.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater
            .from(parent.context)

        val binding: ItemSuggestionBinding
                = DataBindingUtil.inflate(layoutInflater, R.layout.item_suggestion, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return suggestionDetailList.size
    }

    class ViewHolder(private val binding: ItemSuggestionBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(suggestionDetails: SuggestionDetailResponse) {
            /*binding.item = SuggestionDetailItemViewState(suggestionDetails)
            binding.executePendingBindings()*/
        }

    }
}
