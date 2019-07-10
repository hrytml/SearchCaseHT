package com.example.searchcase.ui.search.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.searchcase.R
import com.example.searchcase.data.source.remote.SuggestionResponse
import com.example.searchcase.databinding.ItemSuggestionBinding
import com.example.searchcase.ui.search.SuggestionItemViewState
import javax.inject.Inject

class SuggestionAdapter @Inject constructor() :

    RecyclerView.Adapter<SuggestionAdapter.ViewHolder>() {

    private val suggestionList = mutableListOf<SuggestionResponse>()

    var suggestionItemClickListener: ((String)-> Unit)? = null

    fun setSuggestionList(suggestions: List<SuggestionResponse>) {
        val beforeSize = suggestionList.size

        suggestionList.addAll(suggestions)
        notifyItemRangeInserted(beforeSize,suggestions.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater
                .from(parent.context)

        val binding: ItemSuggestionBinding
                = DataBindingUtil.inflate(layoutInflater, R.layout.item_suggestion, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(suggestionList[position])
    }

    override fun getItemCount(): Int {
        return suggestionList.size
    }

    inner class ViewHolder(private val binding: ItemSuggestionBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener{
                suggestionItemClickListener?.invoke(suggestionList[adapterPosition].text)
            }
        }

        fun bind(suggestion: SuggestionResponse) {

            binding.item = SuggestionItemViewState(suggestion)
            binding.executePendingBindings()
        }
    }


}