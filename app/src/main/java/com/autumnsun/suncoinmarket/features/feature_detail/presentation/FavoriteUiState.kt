package com.autumnsun.suncoinmarket.features.feature_detail.presentation

data class FavoriteUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val successMessage: String = "",
    val failedMessage: String = ""
)
