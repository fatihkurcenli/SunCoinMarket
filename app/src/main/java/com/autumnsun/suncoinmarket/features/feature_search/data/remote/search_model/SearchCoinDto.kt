package com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model


import com.google.gson.annotations.SerializedName

data class SearchCoinDto(
    @SerializedName("coins")
    val coins: List<Coin>?,
    @SerializedName("exchanges")
    val exchanges: List<Exchange>?
)