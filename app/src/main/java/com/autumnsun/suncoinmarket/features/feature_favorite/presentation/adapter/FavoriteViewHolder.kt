package com.autumnsun.suncoinmarket.features.feature_favorite.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.autumnsun.suncoinmarket.databinding.FavoriteListItemBinding
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel


class FavoriteViewHolder(
    private val binding: FavoriteListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(favoriteCoin: FavoriteCoinModel, coinClickEvent: (FavoriteCoinModel) -> Unit) {
        binding.favoriteCoin = favoriteCoin
        binding.root.setOnClickListener {
            coinClickEvent(favoriteCoin)
        }
    }

    companion object {
        fun create(parent: ViewGroup): FavoriteViewHolder {
            val view = FavoriteListItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return FavoriteViewHolder(view)
        }
    }
}