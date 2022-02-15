package com.autumnsun.suncoinmarket.features.feature_detail.domain.data

data class CoinDetail(
    val name: String,
    val symbol: String,
    val description: Description,
    val hashingAlgorithm: String?,
    val image: Image,
    val marketData: MarketData,
    val lastUpdated: String,
)