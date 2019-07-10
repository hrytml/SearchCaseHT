package com.example.searchcase.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.searchcase.R
import com.example.searchcase.common.DeviceUtils
import com.example.searchcase.data.source.remote.Product
import com.example.searchcase.databinding.ItemSuggestionBinding
import com.example.searchcase.ui.search.SuggestionItemViewState
import javax.inject.Inject
import com.example.searchcase.data.source.remote.SuggestionDetailResponse
import com.example.searchcase.databinding.ItemSuggestionDetailBinding
import com.example.searchcase.ui.search.SuggestionDetailItemViewState as SuggestionDetailItemViewState


class SuggestionDetailAdapter @Inject constructor():

    RecyclerView.Adapter<SuggestionDetailAdapter.ViewHolder>()
    {
    private val suggestionDetailList = mutableListOf<Product>()

    val suggestionDetailItemClickListener: ((String)-> Unit)? = null

    fun setSuggestionDetailList(suggestionDetails: List<Product>) {
        val beforeSize = suggestionDetailList.size

        suggestionDetailList.addAll(suggestionDetails)
        notifyItemRangeInserted(beforeSize,suggestionDetails.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater
            .from(parent.context)

        val binding: ItemSuggestionDetailBinding
                = DataBindingUtil.inflate(layoutInflater, R.layout.item_suggestion_detail, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(suggestionDetailList[position])
    }

    override fun getItemCount(): Int {
        return suggestionDetailList.size
    }

    inner class ViewHolder(private val binding: ItemSuggestionDetailBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener{
                suggestionDetailItemClickListener?.invoke(suggestionDetailList[adapterPosition].toString())
            }
        }

        fun bind(suggestionDetail: Product) {
            binding.item = SuggestionDetailItemViewState(suggestionDetail)

            val imageWidth =
                (DeviceUtils.getDeviceWidth(binding.productImg.context)) - binding.productImg.context.resources.getDimension(
                    R.dimen.margin_16dp
                )
            val requestOption =
                RequestOptions.overrideOf(imageWidth.toInt(), (imageWidth * 0.70).toInt())

            Glide.with(binding.productImg.context)
                .load(suggestionDetail.imageUrl)
                .apply(requestOption)
                .into(binding.productImg)

            binding.executePendingBindings()
        }

    }
}
