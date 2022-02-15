package com.autumnsun.suncoinmarket.features.feature_search.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.Coin

class SearchAdapter(
    private val coinClickEvent: (Coin) -> Unit
) : ListAdapter<Coin, SearchViewHolder>(COIN_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position),coinClickEvent)
    }

    companion object {
        private val COIN_COMPARATOR = object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean =
                oldItem == newItem
        }
    }
}