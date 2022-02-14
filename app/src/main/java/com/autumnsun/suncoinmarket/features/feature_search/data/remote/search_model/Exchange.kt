package com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model


import com.google.gson.annotations.SerializedName

data class Exchange(
    @SerializedName("id")
    val id: String?,
    @SerializedName("large")
    val large: String?,
    @SerializedName("market_type")
    val marketType: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("thumb")
    val thumb: String?
)