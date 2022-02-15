package com.autumnsun.suncoinmarket.features.feature_detail.data.model

import com.google.gson.annotations.SerializedName

data class MarketDataDto(
    @SerializedName("current_price")
    val currentPrice: CurrentPriceDto,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("price_change_24h")
    val priceChange24h: Double,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double,
    @SerializedName("high_24h")
    val highestPrice24h: HighDto,
    @SerializedName("sparkline_7d")
    val sparklineDto7D: SparklineDto,
    @SerializedName("low_24h")
    val lowestPrice24h: LowDto,
)