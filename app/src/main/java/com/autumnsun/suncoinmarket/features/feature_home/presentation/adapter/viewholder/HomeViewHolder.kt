package com.autumnsun.suncoinmarket.features.feature_home.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.autumnsun.suncoinmarket.databinding.HomeListCoinItemBinding
import com.autumnsun.suncoinmarket.features.feature_home.data.model.HomeModelDtoItem


class HomeViewHolder(
    private val binding: HomeListCoinItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(homeCoin: HomeModelDtoItem, homeModelClick: (homeModel: HomeModelDtoItem) -> Unit) {
        binding.homeCoinModel = homeCoin
        binding.root.setOnClickListener {
            homeModelClick(homeCoin)
        }
    }

    companion object {
        fun create(parent: ViewGroup): HomeViewHolder {
            val view = HomeListCoinItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return HomeViewHolder(view)
        }
    }
}