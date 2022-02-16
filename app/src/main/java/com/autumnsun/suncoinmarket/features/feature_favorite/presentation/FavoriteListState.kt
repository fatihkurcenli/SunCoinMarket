package com.autumnsun.suncoinmarket.features.feature_favorite.presentation

import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel

data class FavoriteListState(
    val isLoading: Boolean = false,
    val favoriteCoins: List<FavoriteCoinModel> = emptyList(),
    val errorMessage: String = ""
)
