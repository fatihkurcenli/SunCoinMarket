package com.autumnsun.suncoinmarket.features.feature_detail.domain.data

data class MarketData(
    val currentPrice: CurrentPrice,
    val lastUpdated: String,
    val priceChange24h: Double,
    val priceChangePercentage24h: Double,
    val highestPrice24h: High,
    val sparkline: Sparkline,
    val lowestPrice24h: Low,
)