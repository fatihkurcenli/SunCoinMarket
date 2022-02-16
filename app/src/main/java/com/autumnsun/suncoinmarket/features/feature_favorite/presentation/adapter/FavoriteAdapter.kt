package com.autumnsun.suncoinmarket.features.feature_favorite.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel

class FavoriteAdapter(
    private val coinClickEvent: (FavoriteCoinModel) -> Unit
) : ListAdapter<FavoriteCoinModel, FavoriteViewHolder>(FAVORITE_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position), coinClickEvent)
    }

    companion object {
        private val FAVORITE_COMPARATOR = object : DiffUtil.ItemCallback<FavoriteCoinModel>() {
            override fun areItemsTheSame(
                oldItem: FavoriteCoinModel,
                newItem: FavoriteCoinModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: FavoriteCoinModel,
                newItem: FavoriteCoinModel
            ): Boolean =
                oldItem == newItem
        }
    }
}