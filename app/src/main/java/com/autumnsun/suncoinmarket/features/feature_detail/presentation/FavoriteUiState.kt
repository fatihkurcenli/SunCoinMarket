package com.autumnsun.suncoinmarket.features.feature_detail.presentation

data class FavoriteUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val isFavorite: Boolean = false,
    val failedMessage: String = ""
)
