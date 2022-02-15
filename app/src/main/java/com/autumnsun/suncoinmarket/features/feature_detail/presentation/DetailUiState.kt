package com.autumnsun.suncoinmarket.features.feature_detail.presentation

import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.CoinDetail

data class DetailUiState(
    val isLoading: Boolean = false,
    val detailModel: CoinDetail? = null,
    val errorMessage: String = ""
)
