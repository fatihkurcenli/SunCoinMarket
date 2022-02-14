package com.autumnsun.suncoinmarket.features.feature_search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.autumnsun.suncoinmarket.databinding.SearchListItemBinding
import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.Coin

class SearchViewHolder(
    private val binding: SearchListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cryptoModel: Coin) {
        binding.coin = cryptoModel
    }

    companion object {
        fun create(parent: ViewGroup): SearchViewHolder {
            val view = SearchListItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return SearchViewHolder(view)
        }
    }
}