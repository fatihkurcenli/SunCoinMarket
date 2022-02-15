package com.autumnsun.suncoinmarket.features.feature_detail.data.model

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("description")
    val description: DescriptionDto,
    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String,
    @SerializedName("image")
    val image: ImageDto,
    @SerializedName("market_data")
    val marketData: MarketDataDto,
    @SerializedName("last_updated")
    val lastUpdated: String,
)