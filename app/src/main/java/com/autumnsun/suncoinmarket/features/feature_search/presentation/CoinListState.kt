package com.autumnsun.suncoinmarket.features.feature_search.presentation

import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)