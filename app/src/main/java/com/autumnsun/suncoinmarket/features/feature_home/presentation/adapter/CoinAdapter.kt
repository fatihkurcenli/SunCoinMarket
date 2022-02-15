package com.autumnsun.suncoinmarket.features.feature_home.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.autumnsun.suncoinmarket.features.feature_home.data.model.HomeModelDtoItem
import com.autumnsun.suncoinmarket.features.feature_home.presentation.adapter.viewholder.HomeViewHolder


class CoinAdapter(
    private val coinClickEvent: (coinData: HomeModelDtoItem) -> Unit
) : PagingDataAdapter<HomeModelDtoItem, HomeViewHolder>(COIN_COMPARATOR) {

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, coinClickEvent) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder.create(parent)


    companion object {
        private val COIN_COMPARATOR = object : DiffUtil.ItemCallback<HomeModelDtoItem>() {

            override fun areItemsTheSame(oldItem: HomeModelDtoItem, newItem: HomeModelDtoItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: HomeModelDtoItem, newItem: HomeModelDtoItem) =
                oldItem == newItem
        }
    }
}
